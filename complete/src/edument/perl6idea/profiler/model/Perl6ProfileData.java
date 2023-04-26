package edument.perl6idea.profiler.model;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.util.Function;
import edument.perl6idea.profiler.Perl6ProfileDataManager;
import edument.perl6idea.profiler.ui.Perl6ProfileAllocationsPanel;
import edument.perl6idea.profiler.ui.Perl6ProfileGCPanel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;

public class Perl6ProfileData {
    public static final Logger LOG = Logger.getInstance(Perl6ProfileData.class);
    public static final Function<String, String> RELATED_CALL_NODES_SQL = (table) ->
        "SELECT rs.id, rs.name, rs.file, rs.line," +
        "total(case when c.rec_depth = 0 then c.inclusive_time else 0 end) as inclusive_time," +
        "c.exclusive_time, c.entries " +
        table +
        "JOIN routines rs ON c.routine_id == rs.id " +
        "WHERE pc.routine_id = ? " +
        "GROUP BY c.routine_id " +
        "ORDER BY c.inclusive_time DESC;";
    private final Project myProject;
    private final boolean isImported;
    private Connection connection;
    @Nullable
    private String sqlDataFilePath;
    private String myName;
    private final AtomicBoolean isInitialized = new AtomicBoolean();
    private boolean isNameChanged = false;
    private String myDbPath;

    public Perl6ProfileData(@NotNull Project project, String name, @NotNull File sourceFile, boolean hasToRemoveTheFile) throws IOException, SQLException {
        isImported = !hasToRemoveTheFile;
        myProject = project;
        myName = name;
        sqlDataFilePath = sourceFile.getPath();
        connection = createNewDBConnection();
    }

    public Perl6ProfileData(@NotNull Project project, String name, @NotNull Path filePath, boolean isImported) throws Exception {
        this.isImported = isImported;
        myProject = project;
        myName = name;
        connection = createNewDBConnection(filePath);
    }

    private Connection createNewDBConnection() throws SQLException, IOException {
        try {
            Path filePath = Files.createTempFile("comma-profile-tmp", ".sqlite3");
            myDbPath = filePath.toString();
            return DriverManager.getConnection("jdbc:sqlite:" + filePath);
        } catch (IOException ex) {
            throw new IOException("Cannot create temporary database", ex);
        }
    }

    private Connection createNewDBConnection(Path dbPath) throws SQLException {
        try {
            isInitialized.set(true);
            myDbPath = dbPath.toString();
            return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (SQLException ex) {
            isInitialized.set(false);
            throw ex;
        }
    }

    public void initialize() throws IOException, SQLException {
        if (!isInitialized.compareAndSet(false, true))
            return;

        if (!isImported) {
            Perl6ProfileDataManager manager = myProject.getService(Perl6ProfileDataManager.class);
            if (manager != null) {
                manager.saveProfileResult(this);
            }
        }
        assert sqlDataFilePath != null;
        try (Statement statement = connection.createStatement();
             BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(sqlDataFilePath), StandardCharsets.UTF_8))
        ) {
            for (String line; (line = reader.readLine()) != null;) {
                statement.executeUpdate(line);
            }
        }
    }

    public String getFileName() {
        return myDbPath;
    }

    public boolean isNameChanged() {
        return isNameChanged;
    }

    public void setNameChanged(boolean nameChanged) {
        isNameChanged = nameChanged;
    }

    public String getName() {
        return myName;
    }

    public void setName(String name) {
        isNameChanged = true;
        myName = name;
    }

    public void cancel() {
        // A race?
        if (connection == null)
            return;

        try {
            connection.close();
            connection = null;
        }
        catch (SQLException e) {
            LOG.warn(e);
            connection = null;
        }
    }

    public Perl6ProfileCall getProfileCallById(int callId, int maxRecursion, @Nullable Perl6ProfileCall parent) {
        String sql = "WITH EXPR (REC_LEVEL, PARENT_ID, CALL_ID, ROUTINE_ID, INCLUSIVE, EXCLUSIVE, ENTRIES, SPESH, INLINE, NAME, FILE, LINE) AS ( " +
                     "SELECT 1, ROOT.PARENT_ID, ROOT.id, ROOT.routine_id, ROOT.inclusive_time, ROOT.exclusive_time, ROOT.entries, ROOT.spesh_entries, ROOT.inlined_entries,"  +
                     "r.name, r.file, r.line " +
                     "FROM calls ROOT JOIN routines r ON ROOT.routine_id = r.id " +
                     "WHERE ROOT.id = ?" +
                     "UNION ALL " +
                     "SELECT (REC_LEVEL + 1), CHILD.PARENT_ID, CHILD.id, CHILD.ROUTINE_ID, CHILD.inclusive_time, CHILD.exclusive_time, CHILD.entries, CHILD.spesh_entries, CHILD.inlined_entries, " +
                     "r.name, r.file, r.line " +
                     "FROM EXPR PARENT, calls CHILD JOIN routines r ON CHILD.routine_id = r.id " +
                     "WHERE PARENT.call_id = CHILD.parent_id AND REC_LEVEL < ? " +
                     ") SELECT DISTINCT PARENT_ID, CALL_ID, ROUTINE_ID, INCLUSIVE, EXCLUSIVE, ENTRIES, SPESH, INLINE, NAME, FILE, LINE " +
                     "FROM EXPR ORDER BY CALL_ID; ";

        Map<Integer, Perl6ProfileCall> profileCallMap = new HashMap<>();
        if (parent != null)
            profileCallMap.put(parent.getId(), parent);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, callId);
            statement.setInt(2, maxRecursion);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int parentID = set.getInt(1);
                Perl6ProfileCall call = new Perl6ProfileCall(
                    // ID, PARENT ID
                    set.getInt(2), set.getInt(3),
                    // INCLUSIVE, EXCLUSIVE
                    set.getInt(4), set.getInt(5),
                    // ENTRIES, SPESH, INLINE
                    set.getInt(6), set.getInt(7), set.getInt(8),
                    // NAME, FILE, LINE
                    set.getString(9), set.getString(10), set.getInt(11)
                );
                Perl6ProfileCall parentCallInMap = profileCallMap.get(parentID);
                if (parentCallInMap != null) {
                    parentCallInMap.addCallee(call);
                    call.setParent(parentCallInMap);
                }
                profileCallMap.put(call.getId(), call);
            }
        } catch (SQLException e) {
            LOG.warn(e);
            return null;
        }

        return profileCallMap.get(callId);
    }

    public Map<String, Pair<Integer, Integer>> getModuleNodes() throws SQLException {
        Map<String, Pair<Integer, Integer>> nodes = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet calls = statement
                .executeQuery("SELECT r.file, " +
                              "total(exclusive_time) as exclusive_time, " +
                              "max(inclusive_time) as inclusive_time " +
                              "FROM calls c INNER JOIN routines r ON c.routine_id == r.id " +
                              "GROUP BY r.file ORDER BY c.exclusive_time DESC");
            while (calls.next()) {
                String moduleName;
                Matcher matcher = Perl6ProfileCall.CALL_MODULE_PATTERN.matcher(calls.getString("file"));
                if (matcher.find())
                    moduleName = matcher.group(1);
                else
                    continue;
                nodes.compute(moduleName, (k, v) -> {
                    try {
                        if (v == null)
                            return Pair.create(calls.getInt("inclusive_time"), calls.getInt("exclusive_time"));
                        else
                            return Pair.create(v.first, v.second + calls.getInt("exclusive_time"));
                    } catch (SQLException ex) {
                        return v;
                    }
                });
            }
        }
        return nodes;
    }

    public List<Perl6ProfileCall> getNavigationNodes() throws SQLException {
        List<Perl6ProfileCall> nodes = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet calls = statement
                .executeQuery("SELECT r.id, r.file, r.line, r.name, " +
                              "total(case when rec_depth = 0 then inclusive_time else 0 end) as inclusive_time, " +
                              "total(exclusive_time) as exclusive_time, " +
                              "SUM(c.entries) as entries " +
                              "FROM calls c INNER JOIN routines r ON c.routine_id == r.id " +
                              "GROUP BY r.id ORDER BY c.inclusive_time DESC");
            convertProfilerNodes(nodes, calls);
            // XXX remove(0) has complexity O(n) for an ArrayList we use here,
            // but it's not clear if we should to make `convertProfilerNodes` more specific
            // by adding a check "Is it a first element? Then skip it" for every node iteration.
            // And we are likely to get exact elements using an index quite often, so LinkedList isn't usable here
            nodes.remove(0);
        }
        return nodes;
    }

    public List<Perl6ProfileThread> getThreads() {
        List<Perl6ProfileThread> threadList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet threads = statement
                .executeQuery("SELECT thread_id AS id, root_node AS rootNodeID\n" +
                              "FROM profile WHERE rootNodeID IS NOT NULL ORDER BY id ASC;");
            while (threads.next()) {
                threadList.add(new Perl6ProfileThread(threads.getInt("id"),
                                                      threads.getInt("rootNodeID")));
            }
        } catch (SQLException e) {
            LOG.warn(e);
        }
        return threadList;
    }

    public List<Perl6ProfileGCPanel.GCData> getGC() {
        List<Perl6ProfileGCPanel.GCData> gcList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String GC_DATA_QUERY_STATEMENT = "SELECT\n" +
                                             "    MAX(time) AS max_time,\n" +
                                             "    MIN(start_time) AS earliest_start_time,\n" +
                                             "    TOTAL(retained_bytes) AS retained_bytes,\n" +
                                             "    TOTAL(cleared_bytes) AS cleared_bytes,\n" +
                                             "    TOTAL(promoted_bytes) AS promoted_bytes,\n" +
                                             "    group_concat(thread_id, \",\") AS participants,\n" +
                                             "    full\n" +
                                             "FROM gcs\n" +
                                             "GROUP BY sequence_num\n" +
                                             ";";
            ResultSet gcs = statement
                .executeQuery(GC_DATA_QUERY_STATEMENT);
            while (gcs.next()) {
                gcList.add(new Perl6ProfileGCPanel.GCData(
                    gcs.getLong("max_time"),
                    gcs.getLong("earliest_start_time"), gcs.getLong("promoted_bytes"),
                    gcs.getLong("retained_bytes"), gcs.getLong("cleared_bytes"),
                    gcs.getString("participants"), gcs.getBoolean("full")
                ));
            }
        } catch (SQLException e) {
            LOG.warn(e);
        }
        return gcList;
    }

    public List<Perl6ProfileAllocationsPanel.AllocationData> getAllocatedTypes() {
        List<Perl6ProfileAllocationsPanel.AllocationData> data = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String allocatedTypesQuery = "SELECT\n" +
                                             "t.id, t.name, json_extract(t.extra_info, '$.managed_size') AS managed_size," +
                                             "json_extract(t.extra_info, '$.managed_size') * TOTAL(a.count) AS total_bytes," +
                                             "TOTAL(a.count) AS count, TOTAL(a.replaced) AS optimized\n" +
                                             "FROM allocations a INNER JOIN types t ON a.type_id == t.id\n" +
                                             "GROUP BY t.id\n" +
                                             "ORDER BY json_extract(t.extra_info, '$.managed_size') * total(a.count) DESC\n" +
                                             ";";
            ResultSet allocs = statement.executeQuery(allocatedTypesQuery);
            while (allocs.next()) {
                data.add(new Perl6ProfileAllocationsPanel.AllocationData(
                    allocs.getInt(1), allocs.getString(2),
                    allocs.getLong(3), allocs.getLong(4),
                    allocs.getLong(5), allocs.getLong(6)
                ));
            }
        } catch (SQLException e) {
            LOG.warn(e);
        }

        return data;
    }

    public List<Perl6ProfileAllocationsPanel.AllocatedTypeDetails> getAllocatedTypeData(int type_id) {
        List<Perl6ProfileAllocationsPanel.AllocatedTypeDetails> data = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String GC_DATA_QUERY_STATEMENT = String.format(
                "SELECT\n" +
                "routines.name, routines.file, routines.line,\n" +
                "COUNT(calls.id) as sitecount, TOTAL(calls.entries) as entries,\n" +
                "TOTAL(calls.jit_entries) as jit_entries,\n" +
                "json_extract(types.extra_info, '$.managed_size')\n, total(allocations.count) as allocs,\n" +
                "TOTAL(allocations.replaced) AS optimized\n" +
                "FROM routines INNER JOIN allocations on allocations.call_id == calls.id\n" +
                "INNER JOIN calls ON calls.routine_id = routines.id\n" +
                "INNER JOIN types ON types.id == allocations.type_id\n" +
                "WHERE allocations.type_id = %s\n" +
                "GROUP BY routines.id, allocations.type_id\n" +
                "ORDER BY allocs DESC\n" +
                ";", type_id);
            ResultSet calls = statement.executeQuery(GC_DATA_QUERY_STATEMENT);
            while (calls.next()) {
                data.add(new Perl6ProfileAllocationsPanel.AllocatedTypeDetails(
                    calls.getString(1), calls.getString(2),
                    calls.getInt(3), calls.getInt(4),
                    calls.getLong(5), calls.getLong(6),
                    calls.getInt(7), calls.getLong(8),
                    calls.getLong(9)
                ));
            }
        } catch (SQLException e) {
            LOG.warn(e);
        }
        return data;
    }

    public Perl6ProfileOverviewData getOverviewData() {
        Perl6ProfileOverviewData data = new Perl6ProfileOverviewData();
        try (Statement statement = connection.createStatement()) {
            String OVERVIEW_STATEMENT = String.format(
                "select\n" +
                "    total_time,\n" +
                "    first_entry_time,\n" +
                "    root_node,\n" +
                "    spesh_time as spesh_time\n" +
                "from profile\n" +
                "order by thread_id asc;");
            ResultSet overviewData = statement.executeQuery(OVERVIEW_STATEMENT);
            data.totalTime = 0;
            while (overviewData.next()) {
                data.threads++;
                if (data.speshTime == 0)
                    data.speshTime = overviewData.getInt("spesh_time") / 1000f;
                int tempTotalTime = overviewData.getInt("first_entry_time") + overviewData.getInt("total_time");
                if (tempTotalTime > data.totalTime)
                    data.totalTime = tempTotalTime / 1000f;
            }
                //data.totalTime = overviewData.getInt("total_time");
                // data.threads = overviewData.getInt("threads");
                // data.speshTime = overviewData.getInt("spesh_time");
            //}
            String CALLS_STATEMENT = "select\n" +
                                     "    total(entries) as entries_total,\n" +
                                     "    total(spesh_entries) as spesh_entries_total,\n" +
                                     "    total(jit_entries) as jit_entries_total,\n" +
                                     "    total(inlined_entries) as inlined_entries_total,\n" +
                                     "    total(deopt_one) as deopt_one_total,\n" +
                                     "    total(deopt_all) as deopt_all_total,\n" +
                                     "    total(osr) as osr_total\n" +
                                     "from calls;";
            ResultSet overviewCallData = statement.executeQuery(CALLS_STATEMENT);
            if (overviewCallData.next()) {
                data.spesh = overviewCallData.getInt("spesh_entries_total");
                data.entriesTotal = overviewCallData.getInt("entries_total");
                data.inlineEntriesTotal = overviewCallData.getInt("inlined_entries_total");
                data.jit = overviewCallData.getInt("jit_entries_total");
                data.deopted = overviewCallData.getInt("deopt_one_total");
                data.globalDeopt = overviewCallData.getInt("deopt_all_total");
                data.OSR = overviewCallData.getInt("osr_total");
            }
            String GC_STATEMENT = "select\n" +
                                  "    avg(case when full == 0 then latest_end - earliest end) as avg_minor_time,\n" +
                                  "    min(case when full == 0 then latest_end - earliest end) as min_minor_time,\n" +
                                  "    max(case when full == 0 then latest_end - earliest end) as max_minor_time,\n" +
                                  "    avg(case when full == 1 then latest_end - earliest end) as avg_major_time,\n" +
                                  "    min(case when full == 1 then latest_end - earliest end) as min_major_time,\n" +
                                  "    max(case when full == 1 then latest_end - earliest end) as max_major_time,\n" +
                                  "    total(case when full == 0 then latest_end - earliest end) as total_minor,\n" +
                                  "    total(case when full == 1 then latest_end - earliest end) as total_major,\n" +
                                  "    total(latest_end - earliest) as total," +
                                  "    count(DISTINCT sequence_num) as gc_total,\n" +
                                  "    count(DISTINCT (case when full then sequence_num end)) as full_gc_total\n" +
                                  "    from (select\n" +
                                  "            min(start_time) as earliest,\n" +
                                  "            max(start_time + time) as latest_end,\n" +
                                  "            full, sequence_num\n" +
                                  "        from gcs\n" +
                                  "            group by sequence_num\n" +
                                  "            order by sequence_num asc)";
            ResultSet gcCallData = statement.executeQuery(GC_STATEMENT);
            if (gcCallData.next()) {
                data.gcTotalTime = gcCallData.getInt("total") / 1000f;
                data.gcNumber = gcCallData.getInt("gc_total");
                data.fullGCNumber = gcCallData.getInt("full_gc_total");
                data.totalMajor = gcCallData.getInt("total_minor");
                data.totalMinor = gcCallData.getInt("total_major");
                data.minMinorTime = gcCallData.getInt("min_minor_time");
                data.maxMinorTime = gcCallData.getInt("max_minor_time");
                data.avgMinorTime = gcCallData.getInt("avg_minor_time");
                data.minMajorTime = gcCallData.getInt("min_major_time");
                data.maxMajorTime = gcCallData.getInt("max_major_time");
                data.avgMajorTime = gcCallData.getInt("avg_major_time");
            }
            String ALLOCS_STATEMENT = "select\n" +
                                      "    total(a.jit + a.spesh + a.count) as allocated,\n" +
                                      "    total(a.replaced) as replaced\n" +
                                      "from allocations a;";
            ResultSet overviewAllocsData = statement.executeQuery(ALLOCS_STATEMENT);
            if (overviewAllocsData.next()) {
                data.allocated = overviewCallData.getInt("allocated");
                data.replaced = overviewCallData.getInt("replaced");
            }
        } catch (SQLException e) {
            LOG.warn(e);
        }
        return data;
    }

    private static void convertProfilerNodes(List<Perl6ProfileCall> nodes, ResultSet calls) throws SQLException {
        while (calls.next()) {
            nodes.add(new Perl6ProfileCall(
                -1, calls.getInt("id"),
                calls.getInt("inclusive_time"), calls.getInt("exclusive_time"),
                calls.getInt("entries"), -1, -1,
                calls.getString("name"), calls.getString("file"), calls.getInt("line")
            ));
        }
    }

    private List<Perl6ProfileCall> getRelatedCallNodes(int id, boolean wantCallers) {
        try {
            if (!connection.isClosed()) {
                List<Perl6ProfileCall> calleeList = new ArrayList<>();
                String sql = RELATED_CALL_NODES_SQL.fun(
                    wantCallers ?
                    "FROM calls pc JOIN calls c ON c.id == pc.parent_id " :
                    "FROM calls c JOIN calls pc ON pc.id == c.parent_id ");
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, id);
                    ResultSet callees = statement.executeQuery();
                    convertProfilerNodes(calleeList, callees);
                }
                return calleeList;
            }
        }
        catch (SQLException e) {
            LOG.warn(e);
        }
        return new ArrayList<>();
    }

    public List<Perl6ProfileCall> getCalleeListByCallId(int id) {
        return getRelatedCallNodes(id, false);
    }

    public List<Perl6ProfileCall> getCallerListByCallId(int id) {
        return getRelatedCallNodes(id, true);
    }
}

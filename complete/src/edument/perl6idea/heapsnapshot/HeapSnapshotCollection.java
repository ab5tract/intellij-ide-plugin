package edument.perl6idea.heapsnapshot;

import com.github.luben.zstd.ZstdDirectBufferDecompressingStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class HeapSnapshotCollection {
    /**
     * A reference to the file this data was made from.
     *
     * Necessary because data is read lazily.
     */
    RandomAccessFile inputFile;

    /**
     * The outermost TOC of the file points at one TOC for each snapshot. They go here.
     */
    List<TocEntry> snapshotTocEntries;
    /**
     * TOCs in the file that have metadata; filemeta or snapmeta exist at the moment.
     */
    List<TocEntry> metadataTocEntries;

    /**
     * List of Snapshot objects populated from snapmeta datablocks.
     */
    List<Snapshot> snapshotList;
    /**
     * List of SnapshotData objects, potentially without data, ready to be reified.
     */
    List<SnapshotData> snapshotDataList;

    /**
     * String data, which is made up from multiple datablocks across the file.
     */
    SnapshotData.StringData stringData;
    /**
     * Static Frame data, which is made up from multiple datablocks across the file.
     */
    SnapshotData.StaticFrameData staticFrameData;
    /**
     * Type data, which is made up from multiple datablocks across the file.
     */
    SnapshotData.TypeData typeData;

    /**
     * The filemeta specifies a sub-version of the format for smaller changes to the format.
     */
    Long metadataSubversion;
    /**
     * Time when the heap snapshot collection was created inside MoarVM.
     */
    Long startTime;
    /**
     * PID recorded when the heap snapshot collection was created inside MoarVM.
     */
    Long pid;

    /**
     * The highscore data inside the snapshot is just two blobs, "topIDs" and "topscores",
     * the meaning of the numbers inside is derived from this structure information.
     * By default, moarvm spits out 40 entries for each "highscore table", and the
     * tables that are written out are "types_by_count", "frames_by_count", "types_by_size",
     * and "frames_by_size".
     * The topIDs and topscores blobs will thus contain 40 entries giving IDs of types in one
     * and their count in the other, then 40 entries giving IDs of frames / counts of frames,
     * then 40 more for frames/count and another 40 for types/size.
     */
    HighscoreStructure highscoreStructure;

    public HeapSnapshotCollection(RandomAccessFile inputFile) throws IOException {
        this.inputFile = inputFile;
        long outerTocPosition;
        /* Last written TOC has its address put at the very end of the file */
        final long endOfOuterToc = inputFile.length() - 8;
        inputFile.seek(endOfOuterToc);
        outerTocPosition = readLong(inputFile);

        /* Every individual snapshot in the file has a TOC entry in the outermost table.
        * These are of type "toc" themselves. */
        snapshotTocEntries = new ArrayList<>(16);
        metadataTocEntries = new ArrayList<>(4);

        /* snapshotList contains Snapshot objects which hold metadata, like counts */
        snapshotList = new ArrayList<>(16);
        /* snapshotDataList contains SnapshotData objects which hold (lazily) the actual data from the
         * compressed blobs. */
        snapshotDataList = new ArrayList<>(16);

        /* strings, staticframe data and type data is shared across snapshots, but since the snapshot
        * profiler writes the file one snapshot at a time, there can be additional strings/sfs/types for a
        * subset of the snapshots. Their data just gets concatenated after decompression - the compression
        * is not "linked", however; it's a fresh compression state each time. */
        stringData = new SnapshotData.StringData();
        staticFrameData = new SnapshotData.StaticFrameData();
        typeData = new SnapshotData.TypeData();

        TocEntry[] outerToc = readTocAt(inputFile, outerTocPosition);

        List<List<TocEntry>> partsOfSnapshots = new ArrayList<>();

        stringData = new SnapshotData.StringData();

        for (TocEntry e : outerToc) {
            if (e.kind.equals("filemeta")) {
                inputFile.seek(e.position);
                final String foundKind = readKindAsString(inputFile);
                if (!foundKind.equals(e.kind)) {
                    throw new IllegalStateException("Excepted kind of block to match: " + foundKind + " != " + e.kind);
                }

                int contentLength = Math.toIntExact(readLong(inputFile));

                byte[] content = new byte[contentLength];
                inputFile.readFully(content, 0, contentLength);

                String metaContent = new String(content, StandardCharsets.UTF_8);
                JSONObject metadata = new JSONObject(metaContent);
                highscoreStructure = new HighscoreStructure();
                JSONObject highscoreData = metadata.getJSONObject("highscore_structure");
                highscoreStructure.entryCount = (highscoreData.opt("entry_count") == null) ? 0 : highscoreData.getLong("entry_count");
                highscoreStructure.dataOrder = new ArrayList<>(4);
                JSONArray dataOrder = (highscoreData.opt("data_order") == null) ? new JSONArray() : highscoreData.getJSONArray("data_order");
                for (int index = 0; index < dataOrder.length(); index++) {
                    try {
                        highscoreStructure.dataOrder.add(index, dataOrder.getString(index));
                    } catch (JSONException jsonException) {
                        // TODO logger
                        System.err.println("Metadata contained something that is not a string at index " + index);
                        jsonException.printStackTrace();
                    }
                }

                pid = (metadata.opt("pid") == null) ? 0 : metadata.getLong("pid");
                startTime = (metadata.opt("start_time") == null) ? 0 : metadata.getLong("start_time");
                metadataSubversion = (metadata.opt("subversion") == null) ? 0 : metadata.getLong("subversion");

                metadataTocEntries.add(e);
            } else if (e.kind.equals("toc")) {
                final TocEntry[] innerTocEntries = readTocAt(inputFile, e.position);
                snapshotTocEntries.add(e);
                partsOfSnapshots.add(Arrays.asList(innerTocEntries));
            }
        }
        int snapshotIndex = 0;
        for (List<TocEntry> innerParts : partsOfSnapshots) {
            List<TocEntry> dataPieces = new ArrayList<>(8);
            Snapshot snapshotObject;
            SnapshotData snapshotData = new SnapshotData();
            for (TocEntry innerToc : innerParts) {
                if (innerToc.kind.equals("snapmeta")) {
                    inputFile.seek(innerToc.position);
                    final String foundKind = readKindAsString(inputFile);
                    if (!foundKind.equals(innerToc.kind)) {
                        throw new IllegalStateException("Excepted kind of block to match: " + foundKind + " != " + innerToc.kind);
                    }

                    int contentLength = Math.toIntExact(readLong(inputFile));

                    byte[] content = new byte[contentLength];
                    inputFile.readFully(content, 0, contentLength);

                    String metaContent = new String(content, StandardCharsets.UTF_8);
                    JSONObject metadata = new JSONObject(metaContent);
                    snapshotObject = new Snapshot(snapshotIndex,
                            (metadata.opt("snap_time") == null) ? null : metadata.getLong("snap_time"),
                            (metadata.opt("gc_seq_num") == null) ? null : metadata.getLong("gc_seq_num"),
                            (metadata.opt("total_heap_size") == null) ? null : metadata.getLong("total_heap_size"),
                            (metadata.opt("total_objects") == null) ? null : metadata.getLong("total_objects"),
                            (metadata.opt("total_typeobjects") == null) ? null : metadata.getLong("total_typeobjects"),
                            (metadata.opt("total_stables") == null) ? null : metadata.getLong("total_stables"),
                            (metadata.opt("total_frames") == null) ? null : metadata.getLong("total_frames"),
                            (metadata.opt("total_refs") == null) ? null : metadata.getLong("total_refs")
                    );
                    snapshotList.add(snapshotIndex, snapshotObject);
                    metadataTocEntries.add(innerToc);
                } else {
                    inputFile.seek(innerToc.position);
                    String kind = readKindAsString(inputFile);

                    switch (kind) {
                        case "strings":
                            stringData.stringDataPieces.add(innerToc);
                            readIntoStringHeap(inputFile, innerToc, stringData.strings);
                            break;
                        case "typename":
                            typeData.typenamePieces.add(innerToc);
                            break;
                        case "reprname":
                            typeData.reprnamePieces.add(innerToc);
                            break;
                        case "sfname":
                            staticFrameData.namePieces.add(innerToc);
                            break;
                        case "sfline":
                            staticFrameData.linePieces.add(innerToc);
                            break;
                        case "sffile":
                            staticFrameData.filePieces.add(innerToc);
                            break;
                        case "sfcuid":
                            staticFrameData.cuidPieces.add(innerToc);
                            break;
                        case "colkind":
                        case "colsize":
                        case "coltofi":
                        case "colrfcnt":
                        case "colrfstr":
                        case "colusize":
                        case "refdescr":
                        case "reftrget":
                        case "topIDs":
                        case "topscore":
                            dataPieces.add(innerToc);
                            break;
                        default:
                            // TODO use logger
                            System.err.println("Unrecognized inner kind: " + kind);
                    }
                }
            }
            snapshotData.positionOfToc = snapshotTocEntries.get(snapshotIndex);
            snapshotData.dataLocations = dataPieces;
            snapshotData.sourceFile = inputFile;
            snapshotDataList.add(snapshotIndex, snapshotData);
            snapshotIndex++;
        }
    }

    static Long readLong(RandomAccessFile f) throws IOException {
        byte[] longData = new byte[8];
        f.readFully(longData, 0, 8);
        ByteBuffer b = ByteBuffer.wrap(longData);
        b.order(ByteOrder.LITTLE_ENDIAN);
        return b.getLong();
    }

    static Short readShort(RandomAccessFile f) throws IOException {
        byte[] longData = new byte[2];
        f.readFully(longData, 0, 2);
        ByteBuffer b = ByteBuffer.wrap(longData);
        b.order(ByteOrder.LITTLE_ENDIAN);
        return b.getShort();
    }

    static Object readCompressedDatablock(RandomAccessFile f, TocEntry toc) throws IOException {
        short sizePerEntry = readShort(f);
        /* Blocks start with their size, but since that requires seeking backwards after writing,
        * or buffering a load up front, they sometimes end up 0; we rely on the toc instead, since
        * that is always written afterwards. */
        Long size = readLong(f);

        byte[] wholeBlock = new byte[Math.toIntExact(toc.end - f.getFilePointer())];
        f.readFully(wholeBlock, 0, wholeBlock.length);

        ByteBuffer inputBuffer = ByteBuffer.allocateDirect(wholeBlock.length);
        inputBuffer.put(wholeBlock);
        inputBuffer.flip();

        ZstdDirectBufferDecompressingStream decompressStream = new ZstdDirectBufferDecompressingStream(inputBuffer);

        ByteBuffer resultBuffer = ByteBuffer.allocateDirect((wholeBlock.length >> 3) << 4);
        resultBuffer.order(ByteOrder.LITTLE_ENDIAN);

        Buffer castedBuffer;
        Object finalResult;
        /* we make a very rough estimation that the result will be around
        * 2x as big as the input. It's often better than that, haven't done any
        * measurement of this yet. */
        if (sizePerEntry == 8) {
            castedBuffer = resultBuffer.asLongBuffer();
            finalResult = new long[(wholeBlock.length >> 3) << 4];
        } else if (sizePerEntry == 4) {
            castedBuffer = resultBuffer.asIntBuffer();
            finalResult = new int[(wholeBlock.length >> 2) << 3];
        } else if (sizePerEntry == 2) {
            castedBuffer = resultBuffer.asShortBuffer();
            finalResult = new short[(wholeBlock.length >> 1) << 2];
        } else {
            castedBuffer = resultBuffer.asReadOnlyBuffer();
            finalResult = new byte[(wholeBlock.length >> 1) << 2];
        }

        int pos = 0;

        while (decompressStream.hasRemaining()) {
            try {
                decompressStream.read(resultBuffer);
            } catch (IOException e) {
                // TODO logger
                System.out.println(toc.kind);
                e.printStackTrace();
                return null;
            }
            resultBuffer.flip();
            castedBuffer.rewind();
            castedBuffer.limit(resultBuffer.limit() / sizePerEntry);

            final int amountToTake = castedBuffer.limit() - castedBuffer.position();

            if (castedBuffer instanceof LongBuffer) {
                long[] castedFinalResult = (long[]) finalResult;
                if (pos + amountToTake > castedFinalResult.length) {
                    long[] orig = castedFinalResult;
                    castedFinalResult = new long[Math.max(castedFinalResult.length * 2, castedFinalResult.length + amountToTake)];
                    System.arraycopy(orig, 0, castedFinalResult, 0, orig.length);
                    finalResult = castedFinalResult;
                }
                ((LongBuffer) castedBuffer).get(castedFinalResult, pos, amountToTake);
            } else if (castedBuffer instanceof IntBuffer) {
                int[] castedFinalResult = (int[]) finalResult;
                if (pos + amountToTake > castedFinalResult.length) {
                    int[] orig = castedFinalResult;
                    castedFinalResult = new int[Math.max(castedFinalResult.length * 2, castedFinalResult.length + amountToTake)];
                    System.arraycopy(orig, 0, castedFinalResult, 0, orig.length);
                    finalResult = castedFinalResult;
                }
                ((IntBuffer) castedBuffer).get(castedFinalResult, pos, amountToTake);
            } else if (castedBuffer instanceof ShortBuffer) {
                short[] castedFinalResult = (short[]) finalResult;
                if (pos + amountToTake > castedFinalResult.length) {
                    short[] orig = castedFinalResult;
                    castedFinalResult = new short[Math.max(castedFinalResult.length * 2, castedFinalResult.length + amountToTake)];
                    System.arraycopy(orig, 0, castedFinalResult, 0, orig.length);
                    finalResult = castedFinalResult;
                }
                ((ShortBuffer) castedBuffer).get(castedFinalResult, pos, amountToTake);
            } else {
                byte[] castedFinalResult = (byte[]) finalResult;
                if (pos + amountToTake > castedFinalResult.length) {
                    byte[] orig = castedFinalResult;
                    castedFinalResult = new byte[Math.max(castedFinalResult.length * 2, castedFinalResult.length + amountToTake)];
                    System.arraycopy(orig, 0, castedFinalResult, 0, orig.length);
                    finalResult = castedFinalResult;
                }
                ((ByteBuffer) castedBuffer).get(castedFinalResult, pos, amountToTake);
            }
            pos += amountToTake;
        }

        /* Finally, create an exactly fitting buffer. Perhaps skip trimming if it'd only save
         * a kilobyte or so? */
        if (castedBuffer instanceof LongBuffer) {
            long[] castedFinalResult = (long[]) finalResult;
            if (pos < castedFinalResult.length) {
                long[] trimmed = new long[pos];
                System.arraycopy(castedFinalResult, 0, trimmed, 0, pos);
                return trimmed;
            }
            return castedFinalResult;
        } else if (castedBuffer instanceof IntBuffer) {
            int[] castedFinalResult = (int[]) finalResult;
            if (pos < castedFinalResult.length) {
                int[] trimmed = new int[pos];
                System.arraycopy(castedFinalResult, 0, trimmed, 0, pos);
                return trimmed;
            }
            return castedFinalResult;
        } else if (castedBuffer instanceof ShortBuffer) {
            short[] castedFinalResult = (short[]) finalResult;
            if (pos < castedFinalResult.length) {
                short[] trimmed = new short[pos];
                System.arraycopy(castedFinalResult, 0, trimmed, 0, pos);
                return trimmed;
            }
            return castedFinalResult;
        } else {
            byte[] castedFinalResult = (byte[]) finalResult;
            if (pos < castedFinalResult.length) {
                byte[] trimmed = new byte[pos];
                System.arraycopy(castedFinalResult, 0, trimmed, 0, pos);
                return trimmed;
            }
            return castedFinalResult;
        }
    }

    static void readIntoStringHeap(RandomAccessFile f, TocEntry toc, List<String> strings) throws IOException {
        Long size = readLong(f);

        byte[] wholeBlock = new byte[Math.toIntExact(toc.end - f.getFilePointer())];
        f.readFully(wholeBlock, 0, wholeBlock.length);

        ByteBuffer inputBuffer = ByteBuffer.allocateDirect(wholeBlock.length);
        inputBuffer.put(wholeBlock);
        inputBuffer.flip();

        ZstdDirectBufferDecompressingStream decompressStream = new ZstdDirectBufferDecompressingStream(inputBuffer);

        ByteBuffer resultBuffer = ByteBuffer.allocateDirect(Math.max(ZstdDirectBufferDecompressingStream.recommendedTargetBufferSize(), (wholeBlock.length >> 3) << 4));
        resultBuffer.order(ByteOrder.LITTLE_ENDIAN);

        byte[] finalResult = new byte[(wholeBlock.length >> 1) << 2];

        int pos = 0;

        /* Read the entire compressed block at once */

        while (decompressStream.hasRemaining()) {
            try {
                decompressStream.read(resultBuffer);
            } catch (IOException e) {
                // TODO logger or throw exception further upwards
                /*System.out.println(toc.kind);
                e.printStackTrace();*/
                return;
            }
            resultBuffer.flip();

            final int amountToTake = resultBuffer.limit() - resultBuffer.position();

            if (pos + amountToTake > finalResult.length) {
                byte[] orig = finalResult;
                finalResult = new byte[Math.max(finalResult.length * 2, finalResult.length + amountToTake)];
                System.arraycopy(orig, 0, finalResult, 0, orig.length);
            }
            resultBuffer.get(finalResult, pos, amountToTake);

            pos += amountToTake;
        }

        /* Split apart strings; they are stored as a length prefix and then raw utf8 data */

        ByteBuffer stringReadBuffer = ByteBuffer.wrap(finalResult);
        stringReadBuffer.limit(pos);
        stringReadBuffer.order(ByteOrder.LITTLE_ENDIAN);
        while (stringReadBuffer.hasRemaining()) {
            int strlen = stringReadBuffer.getInt();
            byte[] stringBytes = new byte[strlen];

            stringReadBuffer.get(stringBytes, 0, strlen);
            String toAdd = new String(stringBytes, StandardCharsets.UTF_8);
            strings.add(toAdd);
        }
    }

    static TocEntry[] readTocAt(RandomAccessFile f, long outerTocPosition) throws IOException {
        f.seek(outerTocPosition);

        String outerTocKind = readKindAsString(f);

        if (!outerTocKind.equals("toc")) {
            throw new IllegalStateException("expected end-of-file offset to point at a TOC; saw this instead: " + outerTocKind);
        }

        int tocCount = Math.toIntExact(readLong(f));
        TocEntry[] outerToc = new TocEntry[tocCount];

        for (int ti = 0; ti < tocCount; ti++) {
            byte[] data = new byte[24];
            f.readFully(data, 0, 24);
            outerToc[ti] = new TocEntry(data);
        }
        return outerToc;
    }

    static String readKindAsString(RandomAccessFile f) throws IOException {
        /* Kinds are 8-byte long fields that look very much like ascii strings, but don't really
         * mean anything apart from an identifier that will be matched. However, only up to the first
         * null byte will be used (a few versions of moarvm wrote garbage after the initial piece of text)
         */
        byte[] kind = new byte[8];
        f.readFully(kind, 0, 8);
        return new String(TocEntry.grabKindWithoutZeroes(ByteBuffer.wrap(kind)), StandardCharsets.UTF_8);
    }


    /**
     * Access a snapshot at a given index. Will cause data to be read from the file.
     *
     * @param snapshotIndex Index of the snapshot in question. These indices start at 0 and are contiguous.
     * @return A SnapshotData object with all its data decompressed and ready to be used.
     * @throws ArrayIndexOutOfBoundsException if the snapshot index is invalid.
     * @throws IOException if the file used to create this HeapSnapshotCollection couldn't be read for some reason
     */
    public SnapshotData getSnapshotData(Integer snapshotIndex) throws ArrayIndexOutOfBoundsException, IOException {
        if (snapshotIndex >= snapshotTocEntries.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        SnapshotData snapshotData = snapshotDataList.get(snapshotIndex);
        snapshotData.ensureData();
        return snapshotData;
    }

    static class HighscoreStructure {
        Long entryCount;
        List<String> dataOrder;
    }
}

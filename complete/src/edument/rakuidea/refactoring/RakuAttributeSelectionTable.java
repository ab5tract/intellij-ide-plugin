package edument.rakuidea.refactoring;

import com.intellij.refactoring.classMembers.MemberInfoChange;
import com.intellij.refactoring.classMembers.MemberInfoModel;
import com.intellij.refactoring.ui.AbstractMemberSelectionTable;
import edument.rakuidea.psi.RakuPsiDeclaration;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class RakuAttributeSelectionTable extends AbstractMemberSelectionTable<RakuPsiDeclaration, RakuAttributeInfo> {
    public RakuAttributeSelectionTable(List<RakuAttributeInfo> infos) {
        super(infos, null, null);
    }

    @Override
    protected @Nullable Object getAbstractColumnValue(RakuAttributeInfo memberInfo) {
        return false;
    }

    @Override
    protected boolean isAbstractColumnEditable(int rowIndex) {
        return true;
    }

    @Override
    protected Icon getOverrideIcon(RakuAttributeInfo memberInfo) {
        return EMPTY_OVERRIDE_ICON;
    }

    public static class Perl6AttributeInfoModel extends MyTableModel<RakuPsiDeclaration, RakuAttributeInfo> implements MemberInfoModel<RakuPsiDeclaration, RakuAttributeInfo> {
        public Perl6AttributeInfoModel(RakuAttributeSelectionTable table) {
            super(table);
        }

        @Override
        public void memberInfoChanged(@NotNull MemberInfoChange<RakuPsiDeclaration, RakuAttributeInfo> event) {}

        @Override
        public boolean isMemberEnabled(RakuAttributeInfo member) {
            return true;
        }

        @Override
        public String getColumnName(int column) {
            // There is a method getDisplayNameColumnHeader in the parent
            // class used here. We could, in theory, override it to get the proper label,
            // which looks like the original idea, but the method is marked as static
            // and cannot be overridden so useless
            if (column == DISPLAY_NAME_COLUMN) {
                return "Declaration";
            }
            return super.getColumnName(column);
        }

        @Override
        public boolean isCheckedWhenDisabled(RakuAttributeInfo member) {
            return false;
        }

        @Override
        public boolean isAbstractEnabled(RakuAttributeInfo member) {
            return false;
        }

        @Override
        public boolean isAbstractWhenDisabled(RakuAttributeInfo member) {
            return false;
        }

        @Override
        public Boolean isFixedAbstract(RakuAttributeInfo member) {
            return null;
        }

        @Override
        public int checkForProblems(@NotNull RakuAttributeInfo member) {
            return 0;
        }

        @Nls
        @Override
        public String getTooltipText(RakuAttributeInfo member) {
            return null;
        }
    }
}

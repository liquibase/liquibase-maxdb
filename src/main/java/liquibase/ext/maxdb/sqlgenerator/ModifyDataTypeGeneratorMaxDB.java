package liquibase.ext.maxdb.sqlgenerator;

import liquibase.database.Database;
import liquibase.ext.maxdb.database.MaxDBDatabase;
import liquibase.sqlgenerator.core.ModifyDataTypeGenerator;
import liquibase.statement.core.ModifyDataTypeStatement;

public class ModifyDataTypeGeneratorMaxDB extends ModifyDataTypeGenerator {
    @Override
    public boolean supports(ModifyDataTypeStatement statement, Database database) {
        return database instanceof MaxDBDatabase;
    }

    @Override
    public int getPriority() {
        return PRIORITY_DATABASE;
    }


//    @Override
    protected String getModifyString(Database database) {
        return "MODIFY";
    }

//    @Override
    protected String getPreDataTypeString(Database database) {
        return " ";
    }
}

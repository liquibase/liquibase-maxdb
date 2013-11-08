package liquibase.ext.maxdb.datatype;

import liquibase.database.Database;
import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.core.BlobType;
import liquibase.datatype.core.CurrencyType;
import liquibase.ext.maxdb.database.MaxDBDatabase;

public class CurrencyTypeMaxDB extends CurrencyType {

    @Override
    public int getPriority() {
        return PRIORITY_DATABASE;
    }

    @Override
    public boolean supports(Database database) {
        return database instanceof MaxDBDatabase;
    }

    @Override
    public DatabaseDataType toDatabaseDataType(Database database) {
        return new DatabaseDataType("NUMERIC", 15, 2);
    }
}

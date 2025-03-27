package liquibase.ext.maxdb.snapshot;

import liquibase.database.Database;
import liquibase.ext.maxdb.database.MaxDBDatabase;
import liquibase.snapshot.SnapshotGenerator;
import liquibase.snapshot.jvm.SequenceSnapshotGenerator;
import liquibase.statement.SqlStatement;
import liquibase.statement.core.RawParameterizedSqlStatement;
import liquibase.structure.DatabaseObject;
import liquibase.structure.core.Schema;

public class SequenceSnapshotGeneratorMaxDB extends SequenceSnapshotGenerator {
    @Override
    public int getPriority(Class<? extends DatabaseObject> objectType, Database database) {
        if (database instanceof MaxDBDatabase) {
            return PRIORITY_DATABASE;
        } else {
            return PRIORITY_NONE;
        }
    }

    @Override
    protected SqlStatement getSelectSequenceStatement(Schema schema, Database database) {
        if (database instanceof MaxDBDatabase) {
            return new RawParameterizedSqlStatement("SELECT SEQUENCE_NAME FROM DOMAIN.SEQUENCES WHERE OWNER = '" + schema.getName() + "'");
        }
        return super.getSelectSequenceStatement(schema, database);
    }

    @Override
    public Class<? extends SnapshotGenerator>[] replaces() {
        return new Class[]{SequenceSnapshotGenerator.class};
    }
}

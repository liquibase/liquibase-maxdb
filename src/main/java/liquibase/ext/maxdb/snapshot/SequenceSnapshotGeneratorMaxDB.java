package liquibase.ext.maxdb.snapshot;

import liquibase.database.Database;
import liquibase.ext.maxdb.database.MaxDBDatabase;
import liquibase.snapshot.jvm.SequenceSnapshotGenerator;
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
    protected String getSelectSequenceSql(Schema schema, Database database) {
        return "SELECT SEQUENCE_NAME FROM DOMAIN.SEQUENCES WHERE OWNER = '" + schema.getName() + "'";
    }
}

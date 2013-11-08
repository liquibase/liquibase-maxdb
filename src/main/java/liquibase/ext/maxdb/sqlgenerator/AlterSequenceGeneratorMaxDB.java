package liquibase.ext.maxdb.sqlgenerator;

import liquibase.database.Database;
import liquibase.database.core.DB2Database;
import liquibase.exception.ValidationErrors;
import liquibase.ext.maxdb.database.MaxDBDatabase;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.AlterSequenceGenerator;
import liquibase.statement.core.AlterSequenceStatement;

public class AlterSequenceGeneratorMaxDB extends AlterSequenceGenerator {
    @Override
    public boolean supports(AlterSequenceStatement statement, Database database) {
        return database instanceof MaxDBDatabase;
    }

    @Override
    public int getPriority() {
        return PRIORITY_DATABASE;
    }

    @Override
    public ValidationErrors validate(AlterSequenceStatement alterSequenceStatement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        ValidationErrors errors = super.validate(alterSequenceStatement, database, sqlGeneratorChain);

        errors.checkDisallowedField("ordered", alterSequenceStatement.getOrdered(), database, liquibase.database.core.MaxDBDatabase.class);

        return errors;
    }
}

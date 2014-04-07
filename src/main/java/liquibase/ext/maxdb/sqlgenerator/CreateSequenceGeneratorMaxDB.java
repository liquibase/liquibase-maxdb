package liquibase.ext.maxdb.sqlgenerator;

import liquibase.database.Database;
import liquibase.exception.ValidationErrors;
import liquibase.ext.maxdb.database.MaxDBDatabase;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.CreateSequenceGenerator;
import liquibase.statement.core.CreateSequenceStatement;

public class CreateSequenceGeneratorMaxDB extends CreateSequenceGenerator {
    @Override
    public boolean supports(CreateSequenceStatement statement, Database database) {
        return database instanceof MaxDBDatabase;
    }

    @Override
    public int getPriority() {
        return PRIORITY_DATABASE;
    }

    @Override
    public ValidationErrors validate(CreateSequenceStatement alterSequenceStatement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        ValidationErrors errors = super.validate(alterSequenceStatement, database, sqlGeneratorChain);

        errors.checkDisallowedField("ordered", alterSequenceStatement.getOrdered(), database, liquibase.ext.maxdb.database.MaxDBDatabase.class);

        return errors;
    }
}

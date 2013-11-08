package liquibase.ext.maxdb.sqlgenerator;

import liquibase.database.Database;
import liquibase.ext.maxdb.database.MaxDBDatabase;
import liquibase.sql.Sql;
import liquibase.sql.UnparsedSql;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.DropUniqueConstraintGenerator;
import liquibase.statement.core.DropUniqueConstraintStatement;

public class DropUniqueConstraintGeneratorMaxDB extends DropUniqueConstraintGenerator {

    @Override
    public boolean supports(DropUniqueConstraintStatement statement, Database database) {
        return database instanceof MaxDBDatabase;
    }

    @Override
    public int getPriority() {
        return PRIORITY_DATABASE;
    }

    @Override
    public Sql[] generateSql(DropUniqueConstraintStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        String sql = "DROP INDEX " + database.escapeConstraintName(statement.getConstraintName()) + " ON " + database.escapeTableName(statement.getCatalogName(), statement.getSchemaName(), statement.getTableName());

        return new Sql[] {
                new UnparsedSql(sql, getAffectedUniqueConstraint(statement))
        };

    }
}

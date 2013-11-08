package liquibase.ext.maxdb.sqlgenerator;

import liquibase.database.Database;
import liquibase.ext.maxdb.database.MaxDBDatabase;
import liquibase.sql.Sql;
import liquibase.sql.UnparsedSql;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.RenameViewGenerator;
import liquibase.statement.core.RenameViewStatement;
import liquibase.structure.core.View;

public class RenameViewGeneratorMaxDB extends RenameViewGenerator {
    @Override
    public boolean supports(RenameViewStatement statement, Database database) {
        return database instanceof MaxDBDatabase;
    }

    @Override
    public int getPriority() {
        return PRIORITY_DATABASE;
    }

    @Override
    public Sql[] generateSql(RenameViewStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        String sql = "RENAME VIEW " + database.escapeViewName(statement.getCatalogName(), statement.getSchemaName(), statement.getOldViewName()) + " TO " + database.escapeObjectName(statement.getNewViewName(), View.class);
        return new Sql[]{
                new UnparsedSql(sql,
                        getAffectedOldView(statement),
                        getAffectedNewView(statement)
                )
        };

    }
}

package liquibase.ext.maxdb.sqlgenerator;

import liquibase.database.Database;
import liquibase.exception.ValidationErrors;
import liquibase.sql.Sql;
import liquibase.sql.UnparsedSql;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.DropIndexGenerator;
import liquibase.statement.core.DropIndexStatement;

public class DropIndexGeneratorMaxDB extends DropIndexGenerator {

    @Override
    public ValidationErrors validate(DropIndexStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        ValidationErrors validationErrors = new ValidationErrors();
        validationErrors.checkRequiredField("indexName", statement.getIndexName());
        validationErrors.checkRequiredField("tableName", statement.getTableName());
        return validationErrors;
    }

    @Override
    public Sql[] generateSql(DropIndexStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {
    	Sql[] parentSql = super.generateSql(statement, database, sqlGeneratorChain);
    	if (parentSql.length == 0)
    		// associatedWith - fallbacks
    		return parentSql;

        String schemaName = statement.getTableSchemaName();
        return new Sql[] {new UnparsedSql("DROP INDEX " + database.escapeIndexName(null, null, statement.getIndexName()) + " ON " + database.escapeTableName(statement.getTableCatalogName(), schemaName, statement.getTableName()), getAffectedIndex(statement)) };
    }

}

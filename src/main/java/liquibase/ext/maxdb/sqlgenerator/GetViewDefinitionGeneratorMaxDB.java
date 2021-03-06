package liquibase.ext.maxdb.sqlgenerator;

import liquibase.CatalogAndSchema;
import liquibase.database.Database;
import liquibase.ext.maxdb.database.MaxDBDatabase;
import liquibase.sql.Sql;
import liquibase.sql.UnparsedSql;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.GetViewDefinitionGenerator;
import liquibase.statement.core.GetViewDefinitionStatement;

public class GetViewDefinitionGeneratorMaxDB extends GetViewDefinitionGenerator {
    @Override
    public int getPriority() {
        return PRIORITY_DATABASE;
    }

    @Override
    public boolean supports(GetViewDefinitionStatement statement, Database database) {
        return database instanceof MaxDBDatabase;
    }

    @Override
    public Sql[] generateSql(GetViewDefinitionStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        CatalogAndSchema schema = database.correctSchema(new CatalogAndSchema(statement.getCatalogName(), statement.getSchemaName()));

        if (schema.getSchemaName() != null) {
	        return new Sql[]{
	                new UnparsedSql("SELECT DEFINITION FROM DOMAIN.VIEWDEFS WHERE upper(VIEWNAME)='" + statement.getViewName().toUpperCase() + "' AND OWNER='" + schema.getSchemaName() + "'")
	        };
        } else {
	        return new Sql[]{
	                new UnparsedSql("SELECT DEFINITION FROM DOMAIN.VIEWDEFS WHERE upper(VIEWNAME)='" + statement.getViewName().toUpperCase() + "'")
	        };        	
        }
    }
}
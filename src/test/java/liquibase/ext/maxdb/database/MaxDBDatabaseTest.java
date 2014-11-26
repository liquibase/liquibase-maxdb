package liquibase.ext.maxdb.database;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaxDBDatabaseTest {
    @Test
    public void getShortName() {
        assertEquals("maxdb", new MaxDBDatabase().getShortName());
    }
}
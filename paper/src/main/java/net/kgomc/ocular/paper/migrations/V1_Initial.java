package net.kgomc.ocular.paper.migrations;

import net.kgomc.zelda.database.config.DatabaseType;
import net.kgomc.zelda.database.migration.IMigration;

import java.sql.Connection;
import java.sql.Statement;

public class V1_Initial implements IMigration {
    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "Initial migration";
    }

    private static final String QUERY_SCHEMA = "CREATE SCHEMA IF NOT EXISTS ocular;";

    @Override
    public void migrate(Connection connection, DatabaseType databaseType) throws Exception {
        try(Statement statement = connection.createStatement())
        {
            statement.execute(QUERY_SCHEMA);
        }
    }
}

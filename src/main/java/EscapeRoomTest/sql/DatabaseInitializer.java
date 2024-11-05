package EscapeRoomTest.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class DatabaseInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializer.class);
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    private static final String SCRIPT_PATH = System.getProperty("user.dir") + "/src/main/java/EscapeRoomTest/sql/script/escape_room_test.sql";

    public static void createDatabaseIfAbsent(String dbName) {
        try (Connection serverConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = serverConnection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE " + dbName);
            statement.executeUpdate("USE " + dbName);
            executeSqlScript(statement);
            LOGGER.info("Database '{}' successfully created", dbName);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1007){
                LOGGER.info(e.getMessage());
            } else {
                LOGGER.error("SQL error while creating database '{}': {}", dbName, e.getMessage());
            }
        }catch (IOException e){
            LOGGER.error("Error creating database {}: {}", dbName, e.getMessage());
        }
    }

    private static void executeSqlScript(Statement statement) throws IOException, SQLException {
        String sqlScript = new String(Files.readAllBytes(Paths.get(SCRIPT_PATH)));
        String[] sqlStatements = sqlScript.split(";");
        for (String sql : sqlStatements) {
            sql = sql.trim();
            if (!sql.isEmpty()) {
                statement.execute(sql);
            }
        }
    }
}
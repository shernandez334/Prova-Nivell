package EscapeRoomTest.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DatabaseConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnector.class);
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    private static String DEFAULT_DATABASE;
    private static DatabaseConnector instance;


    private DatabaseConnector(){

    }

    public static DatabaseConnector getInstance(){
        if (instance == null){
            instance = new DatabaseConnector();
        }
        return instance;
    }

    public Connection getConnection(String dbName) {
        Connection connection = null;
        try {
            String databaseUrl = URL + "/" + dbName;
            connection = DriverManager.getConnection(databaseUrl, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.warn("Couldn't connect to the database: {}", e.getMessage());
        }
        return connection;
    }
}
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost/recipe_db";
    private static final String USER = "root";
    private static final String PASS = "root";

    private Connection connection = null;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASS);

        System.out.println("DB connection successful");
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;

            System.out.println("DB disconnect successful");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

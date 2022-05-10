package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost/recipe_db";
    private static final String USER = "root";
    private static final String PASS = "root";

    private Connection connection = null;
    private final boolean autocommit;
    private final boolean verbose;

    public Database(boolean autocommit, boolean verbose) {
        this.autocommit = autocommit;
        this.verbose = verbose;
    }

    public Database() {
        this(true, false);
    }

    public Database(boolean autocommit) {
        this(autocommit, true);
    }


    public void connect() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        connection.setAutoCommit(autocommit);

        if (verbose) {
            System.out.println("DB connection successful");
        }
    }

    public void newTransaction() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        connection.setAutoCommit(false);

        if (verbose) {
            System.out.println("DB transaction started");
        }
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;

            if (verbose) {
                System.out.println("DB disconnect successful");
            }
        }
    }

    public void commit() throws SQLException {
        if (connection != null) {
            connection.commit();

            if (verbose) {
                System.out.println("DB commit successful");
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void rollback() throws SQLException {
        if (connection != null) {
            connection.rollback();
            connection = null;

            if (verbose) {
                System.out.println("DB rollback successful");
            }
        }
    }
}

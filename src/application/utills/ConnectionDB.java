package application.utills;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static final String URL = "jdbc:mysql://localhost:3306/market_system";

    public static Connection getConnection() {
        Connection cnn = null;
        try {
            // Establishing connection with database
            cnn = DriverManager.getConnection(URL,USER, PASSWORD);

        } catch (SQLException sqlException) {
            System.out.println("Failed to connect to database.");
        }
        return cnn;
    }
}

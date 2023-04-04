package dataConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {

    public static Connection getConnection() throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        final String user = "postgres";
        final String password = "clover";
        return DriverManager.getConnection(url, user, password);
    }
}

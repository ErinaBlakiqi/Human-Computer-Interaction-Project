package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static String URL = "jdbc:mysql://localhost:3306/projektiKNK";
    private static String USER = "root";
    private static String PASSWORD = "";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return connection;
    }
}

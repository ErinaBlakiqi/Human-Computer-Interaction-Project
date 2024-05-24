package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/projektiKNK";
        String user = "root";
        String password = "";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection.isValid(1000)) {
                System.out.println("Lidhja me bazën e të dhënave u krijua me sukses!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package database;

import services.DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException{
        String url = "jdbc:mysql://localhost:3307/projektiknk";
        String user = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(
                url, user, password
        );

        if(connection.isValid(1000)){
            System.out.println("Lidhja me baze te te dhenave u krijuar me sukses!");
        }
    }
}

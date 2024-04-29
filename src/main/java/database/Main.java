package database;

import java.sql.*;

public class Main {
    public static void main(String [] args) throws SQLException {

        String url="";
        String user="";
        String password="";
        Connection connection= DriverManager.getConnection(
                url,user,password
        );

        if(connection.isValid(1000)){
            System.out.println("Lidhja me baze te te dhenave u krijua me sukses!");
        }



    }

}


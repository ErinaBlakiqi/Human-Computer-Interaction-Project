package services;

import model.User;
import model.dto.CreateUserDto;
import model.dto.UpdateUserDto;

import repository.UserRepository;



import java.sql.SQLException;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static Connection connection;

    public static  Connection getConnection() throws SQLException{
        if (connection == null || connection.isClosed()){
            String url = "jdbc:mysql://localhost:3306/knk_football";
            String user = "root";
            String password ="";
            connection = DriverManager.getConnection(url,user,password);
        }
        return connection;
    }
}
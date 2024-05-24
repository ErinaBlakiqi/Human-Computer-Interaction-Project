package repository;

import database.DatabaseUtil;
import model.User;
import model.dto.UserDto;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public static boolean create(UserDto userData) {
        String query = """
                INSERT INTO Users (firstName, lastName, username, email, salt, passwordHash)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, userData.getFirstName());
            pst.setString(2, userData.getLastName());
            pst.setString(3, userData.getUsername());
            pst.setString(4, userData.getEmail());
            pst.setString(5, userData.getSalt());
            pst.setString(6, userData.getPasswordHash());
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User getByUsername(String username) {
        String query = "SELECT * FROM Users WHERE username = ? LIMIT 1";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, username);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return getFromResultSet(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static User getFromResultSet(ResultSet result) {
        try {
            int id = result.getInt("userId");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            String username = result.getString("username");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            return new User(id, firstName, lastName, username, email, salt, passwordHash);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

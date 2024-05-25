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
import java.sql.SQLException;

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
            int id = result.getInt("UserId");  // lexojna prej bazes se te dhenave
            String firstName = result.getString("FirstName");
            String lastName = result.getString("LastName");
            String username = result.getString("UserName");
            String email = result.getString("Email");
            String salt = result.getString("Salt");
            String passwordHash = result.getString("PasswordHash");
            return new User(id, firstName, lastName, username, email, salt, passwordHash);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean updatePassword(int userId, String newPasswordHash, String newSalt) {
        String query = "UPDATE Users SET passwordHash = ?, salt = ? WHERE userId = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, newPasswordHash);
            pst.setString(2, newSalt);
            pst.setInt(3, userId);
            int rowsUpdated = pst.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

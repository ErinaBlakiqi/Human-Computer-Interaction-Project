package repository;

import model.User;
import model.dto.UserDto;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public static boolean create(UserDto userData) {
        String query = "INSERT INTO Users (FirstName, LastName, UserName, Email, Salt, PasswordHash, Roli) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, userData.getFirstName());
            pst.setString(2, userData.getLastName());
            pst.setString(3, userData.getUsername());
            pst.setString(4, userData.getEmail());
            pst.setString(5, userData.getSalt());
            pst.setString(6, userData.getPasswordHash());
            pst.setString(7, userData.getRoli());

            int rowsAffected = pst.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);  // Add logging
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User getByUsername(String username) {
        String query = "SELECT * FROM Users WHERE UserName = ? LIMIT 1";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, username);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return getFromResultSet(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static User getFromResultSet(ResultSet result) throws SQLException {
        int id = result.getInt("UserId");
        String firstName = result.getString("FirstName");
        String lastName = result.getString("LastName");
        String username = result.getString("UserName");
        String email = result.getString("Email");
        String salt = result.getString("Salt");
        String passwordHash = result.getString("PasswordHash");
        String role = result.getString("Roli");
        return new User(id, firstName, lastName, username, email, salt, passwordHash, role);
    }
}

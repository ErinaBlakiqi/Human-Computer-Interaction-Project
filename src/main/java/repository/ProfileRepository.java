package repository;

import model.dto.EditProfileDto;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfileRepository {
    public static boolean updateProfile(EditProfileDto editProfileDto){
        Connection conn = DBConnector.getConnection();
        String sql = "UPDATE Profiles SET UserName = ?, Location = ?, ContactNumber = ?, ContactEmail = ?, Bio = ? WHERE UserId = ?";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, editProfileDto.getUserName());
            pst.setString(2, editProfileDto.getLocation());
            pst.setString(3, editProfileDto.getContactNumber());
            pst.setString(4, editProfileDto.getContactEmail());
            pst.setString(5, editProfileDto.getBio());
            pst.setInt(6, editProfileDto.getUserId());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

package repository;

import model.dto.DailyChartDto;
import model.dto.EditProfileDto;
import model.dto.ProfileDto;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileRepository {

    public static ProfileDto fetchProfileData(int userId) {
        String sql = "SELECT UserId, UserName, Location, ContactNumber, ContactEmail, Bio FROM Profiles WHERE UserId = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new ProfileDto(
                        rs.getInt("UserId"),
                        rs.getString("UserName"),
                        rs.getString("Location"),
                        rs.getString("ContactNumber"),
                        rs.getString("ContactEmail"),
                        rs.getString("Bio")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean updateProfile(EditProfileDto editProfileDto) throws SQLException {
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
            if (affectedRows > 0) {
                return true;
            } else {
                System.err.println("No rows affected during profile update for user ID: " + editProfileDto.getUserId());
                return false;
            }
        } catch (SQLException e) {
            System.err.println("SQL error during profile update: " + e.getMessage());
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
    // Fetch bought products data per day
    public List<DailyChartDto> fetchBoughtProductsData(int userId) {
        List<DailyChartDto> data = new ArrayList<>();
        String query = "SELECT DATE(Orders.CreatedAt) AS day, COUNT(*) AS count " +
                "FROM Orders " +
                "WHERE Orders.BuyerId = ? " +
                "GROUP BY day";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new DailyChartDto(rs.getString("day"), rs.getInt("count")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    // Fetch sold products data per day
    public List<DailyChartDto> fetchSoldProductsData(int userId) {
        List<DailyChartDto> data = new ArrayList<>();
        String query = "SELECT DATE(Orders.CreatedAt) AS day, COUNT(*) AS count " +
                "FROM Orders " +
                "INNER JOIN Products ON Orders.ProductId = Products.ProductId " +
                "WHERE Products.SellerId = ? " +
                "GROUP BY day";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new DailyChartDto(rs.getString("day"), rs.getInt("count")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}

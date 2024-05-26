package repository;

import model.dto.DailyChartDto;
import model.dto.EditProfileDto;
import model.dto.ProfileDto;
import model.dto.ProfileOrderDto;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileRepository {

    public ProfileDto fetchProfileData(int userId) {
        String sql = "SELECT u.UserId, u.UserName, u.Email, p.Location, p.ContactNumber, p.Bio " +
                "FROM Users u " +
                "LEFT JOIN Profiles p ON u.UserId = p.ProfileId " +
                "WHERE u.UserId = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new ProfileDto(
                        rs.getInt("UserId"),
                        rs.getString("UserName"),
                        rs.getString("Email"),
                        rs.getString("Location"),
                        rs.getString("ContactNumber"),
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
        String selectSql = "SELECT COUNT(*) AS count FROM Profiles WHERE ProfileId = ?";
        String insertSql = "INSERT INTO Profiles (ProfileId, Location, ContactNumber, Bio) VALUES (?, ?, ?, ?)";
        String updateSql = "UPDATE Profiles SET Location = ?, ContactNumber = ?, Bio = ? WHERE ProfileId = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement selectPst = conn.prepareStatement(selectSql)) {
            selectPst.setInt(1, editProfileDto.getUserId());
            ResultSet rs = selectPst.executeQuery();
            if (rs.next() && rs.getInt("count") == 0) {
                try (PreparedStatement insertPst = conn.prepareStatement(insertSql)) {
                    insertPst.setInt(1, editProfileDto.getUserId());
                    insertPst.setString(2, editProfileDto.getLocation());
                    insertPst.setString(3, editProfileDto.getContactNumber());
                    insertPst.setString(4, editProfileDto.getBio());
                    insertPst.executeUpdate();
                    return true;
                }
            } else {
                try (PreparedStatement updatePst = conn.prepareStatement(updateSql)) {
                    updatePst.setString(1, editProfileDto.getLocation());
                    updatePst.setString(2, editProfileDto.getContactNumber());
                    updatePst.setString(3, editProfileDto.getBio());
                    updatePst.setInt(4, editProfileDto.getUserId());
                    int affectedRows = updatePst.executeUpdate();
                    return affectedRows > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
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
    public List<ProfileOrderDto> fetchLastBoughtItems(int userId) {
        List<ProfileOrderDto> items = new ArrayList<>();
        String query = "SELECT o.CreatedAt, p.ProductName, o.TotalPrice " +
                "FROM Orders o " +
                "JOIN Products p ON o.ProductId = p.ProductId " +
                "WHERE o.BuyerId = ? " +
                "ORDER BY o.CreatedAt DESC " +
                "LIMIT 10";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                items.add(new ProfileOrderDto(
                        rs.getString("CreatedAt"),
                        rs.getString("ProductName"),
                        rs.getInt("TotalPrice")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}

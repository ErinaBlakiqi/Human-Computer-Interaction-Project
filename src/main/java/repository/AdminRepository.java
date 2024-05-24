package repository;

import database.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {

    public int getTotalOrders() {
        String query = "SELECT COUNT(*) AS total FROM Orders";
        return getCount(query);
    }

    public int getPendingOrders() {
        String query = "SELECT COUNT(*) AS total FROM Orders WHERE OrderStatus = 'Pending'";
        return getCount(query);
    }

    public int getCompletedOrders() {
        String query = "SELECT COUNT(*) AS total FROM Orders WHERE OrderStatus = 'Completed'";
        return getCount(query);
    }

    public double getTotalRevenue() {
        String query = "SELECT SUM(TotalPrice) AS totalRevenue FROM Orders";
        return getSum(query);
    }

    public double getDailyRevenue() {
        String query = "SELECT SUM(TotalPrice) AS dailyRevenue FROM Orders WHERE DATE(CreatedAt) = CURDATE()";
        return getSum(query);
    }

    private int getCount(String query) {
        int count = 0;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    private double getSum(String query) {
        double sum = 0.0;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                sum = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }
}

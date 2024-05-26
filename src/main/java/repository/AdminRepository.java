package repository;

import database.DatabaseUtil;
import model.dto.DailyRevenueDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {

    public int getTotalOrders() {
        String query = "SELECT COUNT(*) FROM Orders";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPendingOrders() {
        String query = "SELECT COUNT(*) FROM Orders WHERE OrderStatus = 'Pending'";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCompletedOrders() {
        String query = "SELECT COUNT(*) FROM Orders WHERE OrderStatus = 'Completed'";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getTotalRevenue() {
        String query = "SELECT SUM(TotalPrice) FROM Orders";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getDailyRevenue() {
        String query = "SELECT SUM(TotalPrice) FROM Orders WHERE DATE(CreatedAt) = CURDATE()";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public List<DailyRevenueDto> getDailyRevenueData() {
        List<DailyRevenueDto> dailyRevenueData = new ArrayList<>();
        String query = "SELECT DATE(CreatedAt) AS date, SUM(TotalPrice) AS revenue FROM Orders GROUP BY DATE(CreatedAt)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                DailyRevenueDto dailyRevenueDto = new DailyRevenueDto(resultSet.getDate("date").toLocalDate(), resultSet.getDouble("revenue"));
                dailyRevenueData.add(dailyRevenueDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dailyRevenueData;
    }

    public List<DailyRevenueDto> getMonthlyRevenueData() {
        List<DailyRevenueDto> monthlyRevenueData = new ArrayList<>();
        String query = "SELECT DATE_FORMAT(CreatedAt, '%Y-%m') AS date, SUM(TotalPrice) AS revenue FROM Orders GROUP BY DATE_FORMAT(CreatedAt, '%Y-%m')";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                DailyRevenueDto monthlyRevenueDto = new DailyRevenueDto(resultSet.getDate("date").toLocalDate(), resultSet.getDouble("revenue"));
                monthlyRevenueData.add(monthlyRevenueDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthlyRevenueData;
    }
}

package repository;

import model.dto.SellItemDto;
import services.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellItemRepository {

    public void addItem(SellItemDto item) throws SQLException {
        if (!isCategoryValid(item.getCategoryId())) {
            throw new SQLException("Invalid CategoryId: " + item.getCategoryId());
        }

        String query = "INSERT INTO Products (ProductName, Price, Quantity, CategoryId, SellerId, Status) VALUES (?, ?, ?, ?, ?, 'Active')";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getProductName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setInt(4, item.getCategoryId());
            pstmt.setInt(5, item.getSellerId());
            pstmt.executeUpdate();
        }
    }

    private boolean isCategoryValid(int categoryId) throws SQLException {
        String query = "SELECT 1 FROM Categories WHERE CategoryID = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, categoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public List<SellItemDto> getAllItems() throws SQLException {
        List<SellItemDto> items = new ArrayList<>();
        String query = "SELECT * FROM Products";
        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                SellItemDto item = new SellItemDto();
                item.setProductId(rs.getInt("ProductId"));
                item.setProductName(rs.getString("ProductName"));
                item.setPrice(rs.getDouble("Price"));
                item.setQuantity(rs.getInt("Quantity"));
                item.setCategoryId(rs.getInt("CategoryId"));
                item.setSellerId(rs.getInt("SellerId"));
                item.setStatus(rs.getString("Status"));
                items.add(item);
            }
        }
        return items;
    }

    public List<SellItemDto> getItemsByUserId(int userId) throws SQLException {
        List<SellItemDto> items = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE SellerId = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    SellItemDto item = new SellItemDto();
                    item.setProductId(rs.getInt("ProductId"));
                    item.setProductName(rs.getString("ProductName"));
                    item.setPrice(rs.getDouble("Price"));
                    item.setQuantity(rs.getInt("Quantity"));
                    item.setCategoryId(rs.getInt("CategoryId"));
                    item.setSellerId(rs.getInt("SellerId"));
                    item.setStatus(rs.getString("Status"));
                    items.add(item);
                }
            }
        }
        return items;
    }
}

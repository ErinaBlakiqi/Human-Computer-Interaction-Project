package repository;

import model.dto.SellItemDto;
import services.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellItemRepository {

    public void addItem(SellItemDto item) throws SQLException {
        String query = "INSERT INTO Products (ProductName, SellerId, Price, Quantity, CategoryId, Status) VALUES (?, ?, ?, ?, ?, 'Active')";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getProductName());
            pstmt.setInt(2, item.getSellerId());
            pstmt.setDouble(3, item.getPrice());
            pstmt.setInt(4, item.getQuantity());
            pstmt.setInt(5, item.getCategoryId());
            pstmt.executeUpdate();
        }
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
                    item.setSellerId(rs.getInt("SellerId"));
                    item.setPrice(rs.getDouble("Price"));
                    item.setQuantity(rs.getInt("Quantity"));
                    item.setCategoryId(rs.getInt("CategoryId"));
                    item.setStatus(rs.getString("Status"));
                    items.add(item);
                }
            }
        }
        return items;
    }

    public void updateItem(SellItemDto item) throws SQLException {
        String query = "UPDATE Products SET ProductName=?, Price=?, Quantity=?, CategoryId=? WHERE ProductId=?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getProductName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setInt(4, item.getCategoryId());
            pstmt.setInt(5, item.getProductId());
            pstmt.executeUpdate();
        }
    }

    public void deleteItem(int productId) throws SQLException {
        String query = "DELETE FROM Products WHERE ProductId=?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
        }
    }
}

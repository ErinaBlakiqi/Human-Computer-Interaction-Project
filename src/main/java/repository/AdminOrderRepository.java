package repository;

import model.Order;
import database.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminOrderRepository {

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT OrderID, BuyerID, ProductID, Quantity, TotalPrice, OrderStatus, CreatedAt FROM Orders";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("OrderID"));
                order.setBuyerId(rs.getInt("BuyerID"));
                order.setProductId(rs.getInt("ProductID"));
                order.setQuantity(rs.getInt("Quantity"));
                order.setTotalPrice(rs.getInt("TotalPrice"));
                order.setOrderStatus(rs.getString("OrderStatus"));
                order.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public void addOrder(Order order) throws SQLException {
        String query = "INSERT INTO Orders (ProductId, BuyerId, TotalPrice, OrderStatus, PaymentMethod, Quantity, CreatedAt) " +
                "VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, order.getProductId());
            statement.setInt(2, order.getBuyerId());
            statement.setInt(3, order.getTotalPrice());
            statement.setString(4, order.getOrderStatus());
            statement.setString(5, order.getPaymentMethod());
            statement.setInt(6, order.getQuantity());
            statement.executeUpdate();
        }
    }
}

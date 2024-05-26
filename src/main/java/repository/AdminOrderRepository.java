package repository;

import model.Order;
import services.DBConnector;

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

        try (Connection conn = DBConnector.getConnection();
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
}

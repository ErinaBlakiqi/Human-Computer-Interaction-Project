package repository;

import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepository {
    private Connection connection;

    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    public void addOrder(Order order) throws SQLException {
        String query = "INSERT INTO Orders (ProductId, BuyerId, TotalPrice, OrderStatus, PaymentMethod, CreatedAt) " +
                "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getProductId());
            statement.setInt(2, order.getBuyerId());
            statement.setInt(3, order.getTotalPrice());
            statement.setString(4, order.getOrderStatus());
            statement.setString(5, order.getPaymentMethod());
            statement.executeUpdate();
        }
    }

    
}

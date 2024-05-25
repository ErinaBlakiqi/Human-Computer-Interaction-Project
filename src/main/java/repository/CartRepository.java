package repository;

import model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRepository {
    private Connection connection;

    public CartRepository(Connection connection) {
        this.connection = connection;
    }

    public Cart getCartByUserIdAndProductId(int userId, int productId) throws SQLException {
        String query = "SELECT * FROM Cart WHERE UserId = ? AND ProductId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        statement.setInt(2, productId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Cart cart = new Cart();
            cart.setCartId(resultSet.getInt("CartId"));
            cart.setUserId(resultSet.getInt("UserId"));
            cart.setProductId(resultSet.getInt("ProductId"));
            cart.setQuantity(resultSet.getInt("Quantity"));
            return cart;
        }

        return null;
    }

    public void updateCart(Cart cart) throws SQLException {
        String query = "UPDATE Cart SET Quantity = ? WHERE CartId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, cart.getQuantity());
        statement.setInt(2, cart.getCartId());
        statement.executeUpdate();
    }

    public void addCart(Cart cart) throws SQLException {
        String query = "INSERT INTO Cart (UserId, ProductId, Quantity) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, cart.getUserId());
        statement.setInt(2, cart.getProductId());
        statement.setInt(3, cart.getQuantity());
        statement.executeUpdate();
    }

    public int getTotalPriceByUserId(int userId) throws SQLException {
        String query = "SELECT SUM(p.Price * c.Quantity) AS TotalPrice " +
                "FROM Cart c JOIN Products p ON c.ProductId = p.ProductId " +
                "WHERE c.UserId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("TotalPrice");
        }

        return 0;
    }
}

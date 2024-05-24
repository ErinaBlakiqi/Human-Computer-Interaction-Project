package repository;

import model.AdminProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminProductRepository {
    private Connection connection;

    public AdminProductRepository(Connection connection) {
        this.connection = connection;
    }

    public List<AdminProduct> findAll() {
        List<AdminProduct> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM Products";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                AdminProduct product = new AdminProduct(
                        resultSet.getInt("ProductId"),
                        resultSet.getString("ProductName"),
                        resultSet.getInt("SellerId"),
                        resultSet.getDouble("Price"),
                        resultSet.getInt("Quantity"),
                        resultSet.getInt("CategoryId"),
                        resultSet.getString("status")
                );
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public void deleteProduct(int productId) {
        try {
            String query = "DELETE FROM Products WHERE ProductId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Other CRUD operations
}

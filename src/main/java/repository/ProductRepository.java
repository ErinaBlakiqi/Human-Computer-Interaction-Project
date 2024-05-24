package repository;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Product product = new Product();
            product.setProductId(resultSet.getInt("ProductId"));
            product.setProductName(resultSet.getString("ProductName"));
            product.setSellerId(resultSet.getInt("SellerId"));
            product.setPrice(resultSet.getInt("Price"));
            product.setQuantity(resultSet.getInt("Quantity"));
            product.setCategoryId(resultSet.getInt("CategoryId"));
            product.setStatus(resultSet.getString("status"));
            products.add(product);
        }

        return products;
    }

    // Add other CRUD operations as needed
}

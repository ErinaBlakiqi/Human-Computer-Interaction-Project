package repository;

import model.Product;
import services.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private Connection connection;
    public ProductRepository() throws SQLException {
        this.connection = DBConnector.getConnection();
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.*, u.UserName AS SellerName, c.CName AS CategoryName " +
                "FROM Products p " +
                "JOIN Users u ON p.SellerId = u.UserId " +
                "JOIN Categories c ON p.CategoryId = c.CategoryID";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductId"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setSellerId(resultSet.getInt("SellerId"));
                product.setPrice(resultSet.getInt("Price"));
                product.setQuantity(resultSet.getInt("Quantity"));
                product.setCategoryId(resultSet.getInt("CategoryId"));
                product.setStatus(resultSet.getString("status"));
                product.setSellerName(resultSet.getString("SellerName"));
                product.setCategoryName(resultSet.getString("CategoryName"));
                products.add(product);
            }
        }
        return products;
    }

    public List<Product> searchProductsByName(String productName) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.*, u.UserName AS SellerName, c.CName AS CategoryName " +
                "FROM Products p " +
                "JOIN Users u ON p.SellerId = u.UserId " +
                "JOIN Categories c ON p.CategoryId = c.CategoryID " +
                "WHERE p.ProductName LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + productName + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductId(resultSet.getInt("ProductId"));
                    product.setProductName(resultSet.getString("ProductName"));
                    product.setSellerId(resultSet.getInt("SellerId"));
                    product.setPrice(resultSet.getInt("Price"));
                    product.setQuantity(resultSet.getInt("Quantity"));
                    product.setCategoryId(resultSet.getInt("CategoryId"));
                    product.setStatus(resultSet.getString("status"));
                    product.setSellerName(resultSet.getString("SellerName"));
                    product.setCategoryName(resultSet.getString("CategoryName"));
                    products.add(product);
                }
            }
        }
        return products;
    }
}

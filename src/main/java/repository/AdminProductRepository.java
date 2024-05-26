package repository;

import model.Product;
import services.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminProductRepository {
    private Connection connection;

    public AdminProductRepository() throws SQLException {
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

    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO Products (ProductName, SellerId, Price, Quantity, CategoryId, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getSellerId());
            statement.setInt(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setInt(5, product.getCategoryId());
            statement.setString(6, product.getStatus());
            statement.executeUpdate();
        }
    }

    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE Products SET ProductName = ?, SellerId = ?, Price = ?, Quantity = ?, CategoryId = ?, status = ? WHERE ProductId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getSellerId());
            statement.setInt(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setInt(5, product.getCategoryId());
            statement.setString(6, product.getStatus());
            statement.setInt(7, product.getProductId());
            statement.executeUpdate();
        }
    }

    public void deleteProduct(int productId) throws SQLException {
        deleteOrdersByProductId(productId); // Delete related orders first
        String query = "DELETE FROM Products WHERE ProductId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
        }
    }


    public void deleteOrdersByProductId(int productId) throws SQLException {
        String query = "DELETE FROM orders WHERE ProductId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
        }
    }

}

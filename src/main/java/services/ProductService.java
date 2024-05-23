package services;

import model.Product;
import database.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT ProductID, PName, Description, CName AS Category, QuantityAvailable, Price, Status " +
                "FROM Products " +
                "JOIN Categories ON Products.CategoryID = Categories.CategoryID";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("PName"),
                        rs.getString("Description"),
                        rs.getString("Category"),
                        rs.getInt("QuantityAvailable"),
                        rs.getDouble("Price"),
                        rs.getString("Status")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public void addProduct(Product product) {
        String query = "INSERT INTO Products (PName, Description, CategoryID, QuantityAvailable, Price, Status, SellerID) " +
                "VALUES (?, ?, (SELECT CategoryID FROM Categories WHERE CName = ?), ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getCategory());
            stmt.setInt(4, product.getQuantity());
            stmt.setDouble(5, product.getPrice());
            stmt.setString(6, product.getStatus());
            stmt.setInt(7, 1);  // Example SellerID, update as necessary

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setProductId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        String query = "UPDATE Products SET PName = ?, Description = ?, CategoryID = (SELECT CategoryID FROM Categories WHERE CName = ?), " +
                "QuantityAvailable = ?, Price = ?, Status = ?, UpdatedAt = CURRENT_TIMESTAMP WHERE ProductID = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getCategory());
            stmt.setInt(4, product.getQuantity());
            stmt.setDouble(5, product.getPrice());
            stmt.setString(6, product.getStatus());
            stmt.setInt(7, product.getProductId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) {
        String query = "DELETE FROM Products WHERE ProductID = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, productId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String query = "SELECT CName FROM Categories";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                categories.add(rs.getString("CName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
}

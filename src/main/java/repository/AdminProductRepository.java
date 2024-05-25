package repository;

import model.AdminProduct;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminProductRepository {

    public List<AdminProduct> findAll() {
        List<AdminProduct> products = new ArrayList<>();
        try (Connection connection = DBConnector.getConnection()) {
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
                        resultSet.getString("Status")
                );
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public void deleteProduct(int productId) {
        try (Connection connection = DBConnector.getConnection()) {
            String query = "DELETE FROM Products WHERE ProductId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(AdminProduct product) {
        try (Connection connection = DBConnector.getConnection()) {
            String query = "UPDATE Products SET ProductName = ?, SellerId = ?, Price = ?, Quantity = ?, CategoryId = ?, Status = ? WHERE ProductId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getSellerId());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setInt(5, product.getCategoryId());
            statement.setString(6, product.getStatus());
            statement.setInt(7, product.getProductId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findSellerNameById(int sellerId) {
        String sellerName = "Unknown Seller";
        try (Connection connection = DBConnector.getConnection()) {
            String query = "SELECT UserName FROM Users WHERE UserId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, sellerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                sellerName = resultSet.getString("UserName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sellerName;
    }

    public String findCategoryNameById(int categoryId) {
        String categoryName = "Unknown Category";
        try (Connection connection = DBConnector.getConnection()) {
            String query = "SELECT CName FROM Categories WHERE CategoryID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                categoryName = resultSet.getString("CName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryName;
    }
}

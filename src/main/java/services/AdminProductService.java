package services;

import model.dto.AdminProductDTO;
import model.AdminProduct;
import repository.AdminProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

public class AdminProductService {
    private AdminProductRepository productRepository;
    private Connection connection;

    public AdminProductService(AdminProductRepository productRepository, Connection connection) {
        this.productRepository = productRepository;
        this.connection = connection;
    }

    public List<AdminProductDTO> getAllProducts() {
        List<AdminProduct> products = productRepository.findAll();
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteProduct(int productId) {
        productRepository.deleteProduct(productId);
    }

    public void updateProduct(AdminProduct product) {
        productRepository.updateProduct(product);
    }

    private AdminProductDTO convertToDTO(AdminProduct product) {
        AdminProductDTO dto = new AdminProductDTO(
                product.getProductId(),
                product.getProductName(),
                getSellerName(product.getSellerId()), // Implement this method
                product.getPrice(),
                product.getQuantity(),
                getCategoryName(product.getCategoryId()), // Implement this method
                product.getStatus()
        );
        return dto;
    }

    private String getSellerName(int sellerId) {
        String sellerName = "Unknown Seller";
        try {
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

    private String getCategoryName(int categoryId) {
        String categoryName = "Unknown Category";
        try {
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

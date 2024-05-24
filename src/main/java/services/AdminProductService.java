package services;

import model.dto.AdminProductDTO;
import model.AdminProduct;
import repository.AdminProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class AdminProductService {
    private AdminProductRepository productRepository;

    public AdminProductService(AdminProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<AdminProductDTO> getAllProducts() {
        List<AdminProduct> products = productRepository.findAll();
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteProduct(int productId) {
        productRepository.deleteProduct(productId);
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
        // Implement logic to get seller name
        return "Seller Name"; // Placeholder
    }

    private String getCategoryName(int categoryId) {
        // Implement logic to get category name
        return "Category Name"; // Placeholder
    }
}

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

    public void updateProduct(AdminProduct product) {
        productRepository.updateProduct(product);
    }

    private AdminProductDTO convertToDTO(AdminProduct product) {
        return new AdminProductDTO(
                product.getProductId(),
                product.getProductName(),
                getSellerName(product.getSellerId()),
                product.getPrice(),
                product.getQuantity(),
                getCategoryName(product.getCategoryId()),
                product.getStatus()
        );
    }

    private String getSellerName(int sellerId) {
        String sellerName = "Unknown Seller";
        try {
            sellerName = productRepository.findSellerNameById(sellerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sellerName;
    }

    private String getCategoryName(int categoryId) {
        String categoryName = "Unknown Category";
        try {
            categoryName = productRepository.findCategoryNameById(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryName;
    }
}

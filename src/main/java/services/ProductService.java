package services;

import model.Product;
import repository.ProductRepository;
import model.dto.ProductDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProductDTOs() throws SQLException {
        List<Product> products = productRepository.getAllProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            // Assuming getSellerName and getCategoryName methods exist to fetch these details
            productDTO.setSellerName("SellerName"); // Replace with actual implementation
            productDTO.setPrice(product.getPrice());
            productDTO.setCategoryName("CategoryName"); // Replace with actual implementation
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    public List<ProductDTO> searchProductDTOsByName(String productName) throws SQLException {
        List<Product> products = productRepository.searchProductsByName(productName);
        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setSellerName("SellerName"); // Replace with actual implementation
            productDTO.setPrice(product.getPrice());
            productDTO.setCategoryName("CategoryName"); // Replace with actual implementation
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }
}

package services;

import model.Product;
import repository.CartRepository;
import repository.ProductRepository;
import model.dto.ProductDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private ProductRepository productRepository;
    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public List<ProductDTO> getAllProductDTOs() throws SQLException {
        List<Product> products = productRepository.getAllProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setSellerName(product.getSellerName());
            productDTO.setPrice(product.getPrice());
            productDTO.setCategoryName(product.getCategoryName());
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
            productDTO.setSellerName(product.getSellerName());
            productDTO.setPrice(product.getPrice());
            productDTO.setCategoryName(product.getCategoryName());
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }
}

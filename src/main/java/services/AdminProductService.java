package services;

import model.Product;
import repository.AdminProductRepository;
import model.dto.AdminProductDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminProductService {
    private AdminProductRepository adminProductRepository;

    public AdminProductService() {
        this.adminProductRepository = new AdminProductRepository();
    }

    public List<AdminProductDTO> getAllAdminProductDTOs() throws SQLException {
        List<Product> products = adminProductRepository.getAllProducts();
        List<AdminProductDTO> adminProductDTOs = new ArrayList<>();

        for (Product product : products) {
            AdminProductDTO adminProductDTO = new AdminProductDTO();
            adminProductDTO.setProductId(product.getProductId());
            adminProductDTO.setProductName(product.getProductName());
            adminProductDTO.setSellerName(product.getSellerName());
            adminProductDTO.setPrice(product.getPrice());
            adminProductDTO.setCategoryName(product.getCategoryName());
            adminProductDTO.setQuantity(product.getQuantity());
            adminProductDTO.setStatus(product.getStatus());
            adminProductDTOs.add(adminProductDTO);
        }

        return adminProductDTOs;
    }

    public List<AdminProductDTO> searchAdminProductDTOsByName(String productName) throws SQLException {
        List<Product> products = adminProductRepository.searchProductsByName(productName);
        List<AdminProductDTO> adminProductDTOs = new ArrayList<>();

        for (Product product : products) {
            AdminProductDTO adminProductDTO = new AdminProductDTO();
            adminProductDTO.setProductId(product.getProductId());
            adminProductDTO.setProductName(product.getProductName());
            adminProductDTO.setSellerName(product.getSellerName());
            adminProductDTO.setPrice(product.getPrice());
            adminProductDTO.setCategoryName(product.getCategoryName());
            adminProductDTO.setQuantity(product.getQuantity());
            adminProductDTO.setStatus(product.getStatus());
            adminProductDTOs.add(adminProductDTO);
        }

        return adminProductDTOs;
    }

    public void addProduct(AdminProductDTO adminProductDTO) throws SQLException {
        Product product = new Product();
        product.setProductName(adminProductDTO.getProductName());
        product.setSellerId(adminProductDTO.getSellerId());  // Make sure SellerId is set in AdminProductDTO
        product.setPrice(adminProductDTO.getPrice());
        product.setQuantity(adminProductDTO.getQuantity());
        product.setCategoryId(adminProductDTO.getCategoryId());  // Make sure CategoryId is set in AdminProductDTO
        product.setStatus(adminProductDTO.getStatus());
        adminProductRepository.addProduct(product);
    }

    public void updateProduct(AdminProductDTO adminProductDTO) throws SQLException {
        Product product = new Product();
        product.setProductId(adminProductDTO.getProductId());
        product.setProductName(adminProductDTO.getProductName());
        product.setSellerId(adminProductDTO.getSellerId());  // Make sure SellerId is set in AdminProductDTO
        product.setPrice(adminProductDTO.getPrice());
        product.setQuantity(adminProductDTO.getQuantity());
        product.setCategoryId(adminProductDTO.getCategoryId());  // Make sure CategoryId is set in AdminProductDTO
        product.setStatus(adminProductDTO.getStatus());
        adminProductRepository.updateProduct(product);
    }

    public void deleteProduct(int productId) throws SQLException {
        adminProductRepository.deleteProduct(productId);
    }
}

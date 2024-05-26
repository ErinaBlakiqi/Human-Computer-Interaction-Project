package model.filter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.AdminProductDTO;

public class ProductFilter extends Filter<AdminProductDTO> {

    @Override
    public ObservableList<AdminProductDTO> filter(ObservableList<AdminProductDTO> products, String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            return products;
        }

        ObservableList<AdminProductDTO> filteredProducts = FXCollections.observableArrayList();
        String lowerCaseFilter = searchText.toLowerCase();

        for (AdminProductDTO product : products) {
            if (product.getProductName().toLowerCase().contains(lowerCaseFilter) ||
                    product.getSellerName().toLowerCase().contains(lowerCaseFilter) ||
                    product.getCategoryName().toLowerCase().contains(lowerCaseFilter) ||
                    String.valueOf(product.getPrice()).contains(lowerCaseFilter)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }
}
package services;

import model.dto.SellItemDto;
import repository.SellItemRepository;

import java.sql.SQLException;
import java.util.List;

public class SellItemService {
    private SellItemRepository sellItemRepository = new SellItemRepository();

    public void addItem(SellItemDto item) {
        try {
            sellItemRepository.addItem(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SellItemDto> getAllItems() {
        try {
            return sellItemRepository.getAllItems();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateItem(SellItemDto item) {
        try {
            sellItemRepository.updateItem(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int productId) {
        try {
            sellItemRepository.deleteItem(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

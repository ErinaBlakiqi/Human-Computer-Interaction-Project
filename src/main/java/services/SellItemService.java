package services;

import model.dto.SellItemDto;
import repository.SellItemRepository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class SellItemService {
    private final SellItemRepository repository = new SellItemRepository();

    public List<SellItemDto> getAllItems() {
        try {
            List<SellItemDto> items = repository.getAllItems();
            return items != null ? items : Collections.emptyList();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<SellItemDto> getItemsByUserId(int userId) {
        try {
            List<SellItemDto> items = repository.getItemsByUserId(userId);
            return items != null ? items : Collections.emptyList();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void addItem(SellItemDto item) {
        try {
            repository.addItem(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

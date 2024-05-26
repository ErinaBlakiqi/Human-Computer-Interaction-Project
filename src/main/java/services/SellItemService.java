package services;

import model.dto.SellItemDto;
import repository.SellItemRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class SellItemService {
    private final SellItemRepository repository = new SellItemRepository();

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

    public void updateItem(SellItemDto item) {
        try {
            repository.updateItem(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int productId) {
        try {
            repository.deleteItem(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserExistsAndIsSeller(int userId) {
        // Implement database check to see if user exists and is a seller
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT 1 FROM Users WHERE UserId = ? AND Roli = 'Seller'")) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

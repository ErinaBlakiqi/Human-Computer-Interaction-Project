package services;

import model.Order;
import repository.AdminOrderRepository;

import java.sql.SQLException;
import java.util.List;

public class AdminOrderService {
    private AdminOrderRepository adminOrderRepository = new AdminOrderRepository();

    public List<Order> getAllOrders() {
        return adminOrderRepository.getAllOrders();
    }

    public void addOrder(Order order) {
        try {
            adminOrderRepository.addOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add other admin-specific methods if needed
}

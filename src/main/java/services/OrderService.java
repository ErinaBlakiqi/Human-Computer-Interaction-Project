package services;

import model.Order;
import repository.OrderRepository;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public void addOrder(Order order) {
        try {
            orderRepository.addOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add other CRUD methods if needed
}

package services;

import model.Order;
import repository.OrderRepository;

import java.sql.SQLException;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    public void addOrder(Order order) throws SQLException {
        orderRepository.addOrder(order);
    }
}

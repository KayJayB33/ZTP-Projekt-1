package pl.edu.pk.ztpprojekt1.service.order;

import pl.edu.pk.ztpprojekt1.dao.OrderDao;
import pl.edu.pk.ztpprojekt1.model.Order;

import java.util.List;
import java.util.UUID;

public class OrderService {

    private final OrderDao orderDao;
    private final OrderValidator orderValidator;

    public OrderService(OrderDao orderDao, OrderValidator orderValidator) {
        this.orderDao = orderDao;
        this.orderValidator = orderValidator;
    }

    public Order get(UUID uuid) {
        return orderDao.get(uuid).orElseThrow(() -> new IllegalStateException("Order not found"));
    }

    public List<Order> getAll() {
        return orderDao.getAll();
    }

    public void save(String[] params) {
        Order order = orderValidator.validate(params);
        orderDao.save(order);
    }

    public void update(UUID uuid, String[] params) {
        Order order = orderValidator.validate(params);
        orderDao.update(uuid, order);
    }

    public Order delete(UUID uuid) {
        return orderDao.delete(uuid);
    }
}

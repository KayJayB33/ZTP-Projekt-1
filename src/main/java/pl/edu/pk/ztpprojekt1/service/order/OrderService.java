package pl.edu.pk.ztpprojekt1.service.order;

import pl.edu.pk.ztpprojekt1.dao.OrderDao;
import pl.edu.pk.ztpprojekt1.model.Order;
import pl.edu.pk.ztpprojekt1.service.product.ProductService;
import pl.edu.pk.ztpprojekt1.service.product.ProductServiceFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Klasa dla logiki biznesowej dla zamówienia.
 * Pomost pomiędzy wyświetlaniem a dostępem do danych.
 */
public class OrderService {

    private final OrderDao orderDao;
    private final ProductService productService;
    private final OrderValidator orderValidator;

    public OrderService(OrderDao orderDao, OrderValidator orderValidator) {
        this.orderDao = orderDao;
        this.productService = ProductServiceFactory.getInstance();
        this.orderValidator = orderValidator;
    }

    public Order get(UUID uuid) {
        return orderDao.get(uuid).orElseThrow(() -> new IllegalStateException("Order not found"));
    }

    public List<Order> getAll() {
        return orderDao.getAll();
    }

    public void save(String[] params, Map<Long, String> quantitiesParams) {
        Order order = orderValidator.validate(params);
        Map<Long, Integer> quantities = new HashMap<>(quantitiesParams.size());
        for (Map.Entry<Long, String> param : quantitiesParams.entrySet()) {
            Long id = param.getKey();
            Integer quantity = Integer.parseInt(param.getValue());
            if (quantity < 0 || quantity > productService.get(id).getAvailableQuantity()) {
                throw new IllegalArgumentException("product quantity must be positive number not bigger than available quantity");
            }
            quantities.put(param.getKey(), quantity);
        }
        order.setOrderedProducts(quantities);
        order.setPrice(calculateOrderPrice(quantities));
        orderDao.save(order);
        productService.subtractQuantities(quantities);
    }

    private BigDecimal calculateOrderPrice(Map<Long, Integer> quantities) {
        BigDecimal price = BigDecimal.ZERO;
        for (Map.Entry<Long, Integer> quantity : quantities.entrySet()) {
            if (quantity.getValue().equals(0)) {
                continue;
            }
            BigDecimal productPrice = productService.get(quantity.getKey()).getPrice();
            price = price.add(productPrice.multiply(BigDecimal.valueOf(quantity.getValue())));
        }
        return price;
    }

    public void send(String param) {
        UUID uuid = UUID.fromString(param);
        Order order = get(uuid);
        order.getDeliveryStrategy().send();
        orderDao.update(uuid, order);
    }
}

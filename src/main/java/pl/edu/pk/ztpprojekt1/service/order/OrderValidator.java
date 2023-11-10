package pl.edu.pk.ztpprojekt1.service.order;

import pl.edu.pk.ztpprojekt1.model.Order;
import pl.edu.pk.ztpprojekt1.model.Product;
import pl.edu.pk.ztpprojekt1.service.delivery.DeliveryStrategy;
import pl.edu.pk.ztpprojekt1.service.delivery.PostDelivery;
import pl.edu.pk.ztpprojekt1.service.validator.Validator;

import java.util.List;
import java.util.Objects;

public class OrderValidator implements Validator<Order> {
    @Override
    public Order validate(String[] params) {
        String addressee = Objects.requireNonNull(params[0], "addressee must not be null");
        String address = Objects.requireNonNull(params[1], "address must not be null");
        DeliveryStrategy deliveryStrategy = new PostDelivery(addressee, address);
        Order order = new Order();
        order.setDeliveryStrategy(deliveryStrategy);

        return order;
    }
}

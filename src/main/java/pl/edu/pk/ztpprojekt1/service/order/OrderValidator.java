package pl.edu.pk.ztpprojekt1.service.order;

import org.eclipse.tags.shaded.org.apache.xpath.objects.XNull;
import pl.edu.pk.ztpprojekt1.model.Order;
import pl.edu.pk.ztpprojekt1.model.Product;
import pl.edu.pk.ztpprojekt1.service.delivery.DeliveryStrategy;
import pl.edu.pk.ztpprojekt1.service.delivery.ParcelLockerDelivery;
import pl.edu.pk.ztpprojekt1.service.delivery.PostDelivery;
import pl.edu.pk.ztpprojekt1.service.validator.Validator;

import java.util.List;
import java.util.Objects;

public class OrderValidator implements Validator<Order> {
    @Override
    public Order validate(String[] params) {
        String addressee = Objects.requireNonNull(params[0], "addressee must not be null");
        String address = Objects.requireNonNull(params[1], "address must not be null");
        String deliveryType = Objects.requireNonNull(params[2], "delivery type must not be null");
        DeliveryStrategy deliveryStrategy = switch (deliveryType) {
            case "post-delivery" -> new PostDelivery(address, addressee);
            case "parcel-delivery" -> {
                String parcelLockerId = Objects.requireNonNull(params[3], "parcel locker id must not be null");
                yield new ParcelLockerDelivery(addressee, address, parcelLockerId);
            }
            default -> throw new IllegalArgumentException("invalid delivery type");
        };
        Order order = new Order();
        order.setDeliveryStrategy(deliveryStrategy);

        return order;
    }
}

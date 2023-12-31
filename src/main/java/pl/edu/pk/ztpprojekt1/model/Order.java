package pl.edu.pk.ztpprojekt1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.edu.pk.ztpprojekt1.service.delivery.DeliveryStrategy;
import pl.edu.pk.ztpprojekt1.util.MoneySerializer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Klasa reprezentująca zamówienie
 */
public class Order {
    private final UUID uuid;
    private final Instant createdOn;
    @JsonProperty("ordered_products")
    private Map<Long, Integer> orderedProducts;
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal price;
    @JsonProperty("delivery_strategy")
    private DeliveryStrategy deliveryStrategy;

    public Order() {
        this.uuid = UUID.randomUUID();
        this.createdOn = Instant.now();
    }

    public Order(Map<Long, Integer> orderedProducts, BigDecimal price, DeliveryStrategy deliveryStrategy) {
        this();
        this.orderedProducts = orderedProducts;
        this.price = price;
        this.deliveryStrategy = deliveryStrategy;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Map<Long, Integer> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Map<Long, Integer> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public DeliveryStrategy getDeliveryStrategy() {
        return deliveryStrategy;
    }

    public void setDeliveryStrategy(DeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(uuid, order.uuid) && Objects.equals(createdOn, order.createdOn) && Objects.equals(orderedProducts, order.orderedProducts) && Objects.equals(price, order.price) && Objects.equals(deliveryStrategy, order.deliveryStrategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, createdOn, orderedProducts, price, deliveryStrategy);
    }

    @Override
    public String toString() {
        return "Order{" +
                "uuid=" + uuid +
                ", createdOn=" + createdOn +
                ", orderedProducts=" + orderedProducts +
                ", price=" + price +
                ", deliveryStrategy=" + deliveryStrategy +
                '}';
    }
}

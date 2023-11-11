package pl.edu.pk.ztpprojekt1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.edu.pk.ztpprojekt1.util.MoneySerializer;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Klasa reprezentująca produkt
 */
public class Product {
    private static long counter = 1L;
    private final long id;
    private String name;
    private String description;
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal price;
    @JsonProperty("available_quantity")
    private int availableQuantity;

    public Product() {
        this.id = counter++;
    }

    public Product(String name, String description, BigDecimal price, int availableQuantity) {
        this.id = counter++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    public Long getId() {
        return id;
    }

    public static void setCounter(long count) {
        counter = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && availableQuantity == product.availableQuantity && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, availableQuantity);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity +
                '}';
    }
}

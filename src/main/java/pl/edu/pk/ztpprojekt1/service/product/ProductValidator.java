package pl.edu.pk.ztpprojekt1.service.product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.pk.ztpprojekt1.model.Product;
import pl.edu.pk.ztpprojekt1.service.validator.Validator;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductValidator implements Validator {
    private static final Logger logger = LogManager.getLogger(ProductValidator.class);
    @Override
    public Product validate(String[] params) {
        try {
            Objects.requireNonNull(params[0], "name must not be null");
            Objects.requireNonNull(params[1], "description must not be null");
            Objects.requireNonNull(params[2], "price must not be null");
            Objects.requireNonNull(params[3], "available quantity must not be null");

            String name = params[0];
            String description = params[1];
            BigDecimal price = new BigDecimal(params[2]);
            int availableQuantity = Integer.parseInt(params[3]);

            if(name.isBlank()) {
                throw new IllegalArgumentException("name must not be empty nor blank");
            }

            if(description.isBlank()) {
                throw new IllegalArgumentException("description must not be empty nor blank");
            }

            if (availableQuantity < 0) {
                throw new IllegalArgumentException("available quantity must not be negative");
            }

            return new Product(name, description, price, availableQuantity);
        } catch (Exception e) {
            logger.error("Error during validation: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

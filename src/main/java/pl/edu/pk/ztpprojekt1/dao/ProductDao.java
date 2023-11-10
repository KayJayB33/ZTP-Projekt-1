package pl.edu.pk.ztpprojekt1.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.pk.ztpprojekt1.model.Product;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class ProductDao implements Dao<Long, Product> {
    private final List<Product> products;
    private final File productsJsonFile;
    private final ObjectMapper mapper;
    private static final Logger logger = LogManager.getLogger(ProductDao.class);

    public ProductDao(File jsonFile, ObjectMapper mapper) {
        this.productsJsonFile = jsonFile;
        this.mapper = mapper;
        this.products = readFromJsonFile(productsJsonFile);
        final long maxId = products.stream()
                .mapToLong(Product::getId)
                .max()
                .orElse(1L);
        Product.setCounter(maxId + 1);
    }

    private List<Product> readFromJsonFile(File productsJsonFile) {
        try {
            return new ArrayList<>(Arrays.asList(mapper.readValue(productsJsonFile, Product[].class)));
        } catch (IOException e) {
            logger.error(String.format("Unable to read from %s file: %s", productsJsonFile, e.getMessage()), e);
        }

        return new ArrayList<>();
    }

    private void saveToJsonFile(File productsJsonFile) {
        try {
            mapper.writeValue(productsJsonFile, products);
        } catch (IOException e) {
            logger.error(String.format("Unable to write to %s file: %s", productsJsonFile, e.getMessage()), e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        products.add(product);
        saveToJsonFile(productsJsonFile);
        logger.info("Product saved to database: " + product);
    }

    @Override
    public void update(Long id, Product product) {
        products.replaceAll(p -> p.getId() == id ? product : p);
        saveToJsonFile(productsJsonFile);
        logger.info(String.format("Product with id %d updated with data: %s", id, product));
    }

    @Override
    public Product delete(Long id) {
        Optional<Product> optionalProduct = get(id);
        if (optionalProduct.isEmpty()) {
            return null;
        }
        Product product = optionalProduct.get();
        products.remove(product);
        saveToJsonFile(productsJsonFile);
        logger.info(String.format("Product with id %d deleted", id));
        return product;
    }
}

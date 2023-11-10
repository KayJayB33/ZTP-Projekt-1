package pl.edu.pk.ztpprojekt1.service.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.pk.ztpprojekt1.dao.ProductDao;
import pl.edu.pk.ztpprojekt1.util.JsonFileHandler;

import java.io.File;
import java.io.IOException;

public class ProductServiceFactory {
    private static final String PRODUCTS_JSON_FILEPATH = "data/products.json";
    private static final Logger logger = LogManager.getLogger(ProductServiceFactory.class);
    private static ProductService productService;


    public static ProductService getInstance() {
        if(productService != null) {
            return productService;
        }

        File productsJsonFile = new File(PRODUCTS_JSON_FILEPATH);
        try {
            JsonFileHandler.createFileIfNotExists(productsJsonFile);
        } catch (IOException e) {
            logger.error("Error during file initialization: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }

        ProductDao productDao = new ProductDao(productsJsonFile, new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT));
        productService = new ProductService(productDao, new ProductValidator());
        return productService;
    }
}

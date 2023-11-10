package pl.edu.pk.ztpprojekt1.service.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.pk.ztpprojekt1.dao.OrderDao;
import pl.edu.pk.ztpprojekt1.util.JsonFileHandler;

import java.io.File;
import java.io.IOException;

public class OrderServiceFactory {
    private static OrderService orderService;
    private static final String ORDERS_JSON_FILEPATH = "data/orders.json";
    private static final Logger logger = LogManager.getLogger(OrderServiceFactory.class);

    public static OrderService getInstance() {
        if(orderService != null) {
            return orderService;
        }

        File ordersJsonFile = new File(ORDERS_JSON_FILEPATH);
        try {
            JsonFileHandler.createFileIfNotExists(ordersJsonFile);
        } catch (IOException e) {
            logger.error("Error during file initialization: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }

        OrderDao orderDao = new OrderDao(ordersJsonFile, new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)
                .findAndRegisterModules());
        orderService = new OrderService(orderDao, new OrderValidator());
        return orderService;
    }
}

package pl.edu.pk.ztpprojekt1.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.pk.ztpprojekt1.model.Order;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class OrderDao implements Dao<UUID, Order> {
    private final List<Order> orders;
    private final File ordersJsonFile;
    private final ObjectMapper mapper;
    private static final Logger logger = LogManager.getLogger(OrderDao.class);

    public OrderDao(File jsonFile, ObjectMapper mapper) {
        this.ordersJsonFile = jsonFile;
        this.mapper = mapper;
        this.orders = readFromJsonFile(ordersJsonFile);
    }

    private List<Order> readFromJsonFile(File ordersJsonFile) {
        try {
            return new ArrayList<>(Arrays.asList(mapper.readValue(ordersJsonFile, Order[].class)));
        } catch (IOException e) {
            logger.error(String.format("Unable to read from %s file: %s", ordersJsonFile, e.getMessage()), e);
        }

        return new ArrayList<>();
    }

    private void saveToJsonFile(File ordersJsonFile) {
        try {
            mapper.writeValue(ordersJsonFile, orders);
        } catch (IOException e) {
            logger.error(String.format("Unable to write to %s file: %s", ordersJsonFile, e.getMessage()), e);
        }
    }

    @Override
    public Optional<Order> get(UUID uuid) {
        return orders.stream().filter(p -> p.getUuid().equals(uuid)).findFirst();
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public void save(Order order) {
        orders.add(order);
        saveToJsonFile(ordersJsonFile);
        logger.info("Product saved to database: " + order);
    }

    @Override
    public void update(UUID uuid, Order order) {
        orders.replaceAll(o -> o.getUuid().equals(uuid) ? order : o);
        saveToJsonFile(ordersJsonFile);
        logger.info(String.format("Order with id %s updated with data: %s", uuid, order));
    }

    @Override
    public Order delete(UUID uuid) {
        Optional<Order> optionalOrder = get(uuid);
        if (optionalOrder.isEmpty()) {
            return null;
        }
        Order order = optionalOrder.get();
        orders.remove(order);
        saveToJsonFile(ordersJsonFile);
        logger.info(String.format("Order with id %s deleted", uuid));
        return order;
    }
}

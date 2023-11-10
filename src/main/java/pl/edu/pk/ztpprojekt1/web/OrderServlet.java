package pl.edu.pk.ztpprojekt1.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.pk.ztpprojekt1.dao.OrderDao;
import pl.edu.pk.ztpprojekt1.model.Order;
import pl.edu.pk.ztpprojekt1.service.order.OrderService;
import pl.edu.pk.ztpprojekt1.service.order.OrderValidator;
import pl.edu.pk.ztpprojekt1.util.JsonFileHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/orders", "/orders/new", "/orders/update", "/orders/delete", "/orders/send"})
public class OrderServlet extends HttpServlet {
    private static final String ORDERS_JSON_FILEPATH = "data/orders.json";
    private OrderService orderService;
    private static final Logger logger = LogManager.getLogger(ProductServlet.class);

    @Override
    public void init() {

        File ordersJsonFile = new File(ORDERS_JSON_FILEPATH);
        try {
            JsonFileHandler.createFileIfNotExists(ordersJsonFile);
        } catch (IOException e) {
            logger.error("Error during file initialization: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }

        OrderDao orderDao = new OrderDao(ordersJsonFile, new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT));
        orderService = new OrderService(orderDao, new OrderValidator());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/orders":
                listOrders(req, resp);
                break;
        }
    }

    private void listOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.getAll();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/orders/orders.jsp").forward(req, resp);
    }
}

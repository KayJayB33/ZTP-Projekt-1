package pl.edu.pk.ztpprojekt1.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.edu.pk.ztpprojekt1.model.Order;
import pl.edu.pk.ztpprojekt1.model.Product;
import pl.edu.pk.ztpprojekt1.service.order.OrderService;
import pl.edu.pk.ztpprojekt1.service.order.OrderServiceFactory;
import pl.edu.pk.ztpprojekt1.service.product.ProductService;
import pl.edu.pk.ztpprojekt1.service.product.ProductServiceFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet odpowiedzialny za obsługę zamówień.
 * W przypadku zapytań GET przekierowuje do listy zamówień lub formularza
 * nowego zamówienia.
 * W przypadku zapytania POST w zaleźności od ścieżki zapytania tworzy nowe zamówienie
 * lub wysyła zamówienie.
 */
@WebServlet(urlPatterns = {"/orders", "/orders/create", "/orders/send"})
public class OrderServlet extends HttpServlet {
    private OrderService orderService;
    private ProductService productService;

    @Override
    public void init() {
        orderService = OrderServiceFactory.getInstance();
        productService = ProductServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/orders":
                req.setAttribute("products", productService.getAll());
                listOrders(req, resp);
                break;
            case "/orders/create":
                showCreateOrderForm(req, resp);
                break;
            case "/orders/send":
                doPost(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().equals("/orders/send")) {
            String uuid = req.getParameter("id");
            orderService.send(uuid);
            resp.sendRedirect("/orders");
            return;
        }

        String addressee = req.getParameter("addressee");
        String address = req.getParameter("address");
        List<Product> products = productService.getAll();
        Map<Long, String> productsQuantities = new HashMap<>(products.size());
        for (Product product : products) {
            productsQuantities.put(product.getId(), req.getParameter(product.getId() + "-quantity"));
        }
        orderService.save(new String[]{addressee, address}, productsQuantities);
        resp.sendRedirect("/orders");
    }

    private void showCreateOrderForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/orders/order-form.jsp").forward(req, resp);
    }

    private void listOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.getAll();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/orders/orders.jsp").forward(req, resp);
    }
}

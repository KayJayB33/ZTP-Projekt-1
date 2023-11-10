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
import pl.edu.pk.ztpprojekt1.dao.ProductDao;
import pl.edu.pk.ztpprojekt1.model.Product;
import pl.edu.pk.ztpprojekt1.service.product.ProductService;
import pl.edu.pk.ztpprojekt1.service.product.ProductServiceFactory;
import pl.edu.pk.ztpprojekt1.service.product.ProductValidator;
import pl.edu.pk.ztpprojekt1.util.JsonFileHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/products", "/products/create", "/products/update", "/products/delete"})
public class ProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() {
        productService = ProductServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/products":
                listProducts(req, resp);
                break;
            case "/products/create":
                showNewProductForm(req, resp);
                break;
            case "/products/update":
                showUpdateProductForm(req, resp);
                break;
            case "/products/delete":
                doDelete(req, resp);
                resp.sendRedirect("/products");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().equals("/products/update")) {
            doPut(req, resp);
            resp.sendRedirect("/products");
            return;
        }

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        String availableQuantity = req.getParameter("quantity");
        productService.save(new String[]{name, description, price, availableQuantity});
        resp.sendRedirect("/products");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        String availableQuantity = req.getParameter("quantity");
        productService.update(id, new String[]{name, description, price, availableQuantity});
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        productService.delete(id);
    }

    private void showNewProductForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/products/product-form.jsp").forward(req, resp);
    }

    private void showUpdateProductForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Product existingProduct = productService.get(id);
        req.setAttribute("product", existingProduct);
        req.getRequestDispatcher("/products/product-form.jsp").forward(req, resp);
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.getAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/products/products.jsp").forward(req, resp);
    }
}

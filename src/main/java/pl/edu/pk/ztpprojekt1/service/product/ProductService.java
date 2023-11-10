package pl.edu.pk.ztpprojekt1.service.product;

import pl.edu.pk.ztpprojekt1.dao.ProductDao;
import pl.edu.pk.ztpprojekt1.model.Product;
import pl.edu.pk.ztpprojekt1.service.validator.Validator;

import java.util.List;

public class ProductService {
    private final ProductDao productDao;
    private final Validator<Product> validator;

    public ProductService(ProductDao productDao, Validator<Product> validator) {
        this.productDao = productDao;
        this.validator = validator;
    }

    public Product get(long id) {
        return productDao.get(id).orElseThrow(() -> new IllegalStateException("Product not found"));
    }

    public List<Product> getAll() {
        return productDao.getAll();
    }

    public void save(String[] params) {
        Product product = validator.validate(params);
        productDao.save(product);
    }

    public void update(long id, String[] params) {
        Product product = validator.validate(params);
        productDao.update(id, product);
    }

    public Product delete(long id) {
        return productDao.delete(id);
    }
}

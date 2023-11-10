package pl.edu.pk.ztpprojekt1.service.product;

import pl.edu.pk.ztpprojekt1.dao.ProductDao;
import pl.edu.pk.ztpprojekt1.model.Product;
import pl.edu.pk.ztpprojekt1.service.validator.Validator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductService {
    private final ProductDao productDao;
    private final ProductValidator validator;

    public ProductService(ProductDao productDao, ProductValidator validator) {
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

    public void subtractQuantities(Map<Long, Integer> quantities) {
        for (Map.Entry<Long, Integer> quantity : quantities.entrySet()) {
            Long id = quantity.getKey();
            Optional<Product> optionalProduct = productDao.get(id);
            if(optionalProduct.isEmpty()) {
                continue;
            }
            Product product = optionalProduct.get();
            product.setAvailableQuantity(product.getAvailableQuantity() - quantity.getValue());
            productDao.update(id, product);
        }
    }
}

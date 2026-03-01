package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService extends BaseService<Product>{
    @Override
    Product create(Product product);
    @Override
    List<Product> findAll();
    @Override
    Product findById(String productId);
    @Override
    Product update(String id, Product product);
    @Override
    void deleteById(String productId);
}

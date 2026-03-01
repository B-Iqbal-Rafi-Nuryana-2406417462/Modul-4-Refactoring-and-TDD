package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;


@Repository
public class ProductRepository extends AbstractInMemoryRepository<Product>{

    @Override
    protected String getId(final Product product) {
        return product.getProductId();
    }

    @Override
    protected void setId(final Product product,final String id) {
        product.setProductId(id);
    }

    @Override
    protected void copyField(final Product existingProduct,final Product updatedProduct) {
        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setProductQuantity(updatedProduct.getProductQuantity());
    }
}

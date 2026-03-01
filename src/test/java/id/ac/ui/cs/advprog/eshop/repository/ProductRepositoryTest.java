package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){

    }

    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("P002");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("P002");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("P003");
        product2.setProductName("Sabun Mandi Aroma Jeruk");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(),savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdFound() {
        Product product = new Product();
        product.setProductId("P001");
        product.setProductName("Sabun Cuci Piring");
        product.setProductQuantity(75);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("P001");
        assertNotNull(foundProduct);
        assertEquals("P001", foundProduct.getProductId());
        assertEquals("Sabun Cuci Piring", foundProduct.getProductName());
        assertEquals(75, foundProduct.getProductQuantity());
    }

    @Test
    void testFindByIdNotFound() {
        Product product = new Product();
        product.setProductId("P001");
        product.setProductName("Sabun Cuci Piring");
        product.setProductQuantity(75);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("P999");
        assertNull(foundProduct);
    }

    @Test
    void testEditExistingProduct_ShouldUpdateSuccessfully() {
        Product product = new Product();
        product.setProductId("P001");
        product.setProductName("Sabun Cuci Piring");
        product.setProductQuantity(75);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("P001");
        updatedProduct.setProductName("Sabun Cuci Piring Ultra");
        updatedProduct.setProductQuantity(100);

        Product savedProduct = productRepository.update(updatedProduct.getProductId(), updatedProduct);
        assertNotNull(savedProduct);
        assertEquals("Sabun Cuci Piring Ultra", savedProduct.getProductName());
        assertEquals(100, savedProduct.getProductQuantity());

        Product foundProduct = productRepository.findById("P001");
        assertEquals("Sabun Cuci Piring Ultra", foundProduct.getProductName());
        assertEquals(100, foundProduct.getProductQuantity());
    }

    @Test
    void testEditMissingProduct_ShouldReturnNull() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("P404");
        updatedProduct.setProductName("Produk Tidak Ada");
        updatedProduct.setProductQuantity(1);

        Product savedProduct = productRepository.update(updatedProduct.getProductId(), updatedProduct);

        assertNull(savedProduct);
        assertNull(productRepository.findById("P404"));
    }

    @Test
    void testDeleteExistingProduct_ShouldRemoveFromRepository() {
        Product product = new Product();
        product.setProductId("P999");
        product.setProductName("Produk Percobaan");
        product.setProductQuantity(10);
        productRepository.create(product);

        productRepository.deleteById("P999");

        assertNull(productRepository.findById("P999"));
    }

    @Test
    void testDeleteMissingProduct_ShouldNotChangeRepository() {
        Product product = new Product();
        product.setProductId("P111");
        product.setProductName("Produk Tetap Ada");
        product.setProductQuantity(5);
        productRepository.create(product);

        productRepository.deleteById("P999");

        Product stillThere = productRepository.findById("P111");
        assertNotNull(stillThere);
        assertEquals("P111", stillThere.getProductId());
    }

}

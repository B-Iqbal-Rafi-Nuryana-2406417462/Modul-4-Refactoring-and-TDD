package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    private  PaymentRepository paymentRepository;
    private Order order;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("p-1");
        product.setProductName("Product 1");
        product.setProductQuantity(2);
        products.add(product);
        order = new Order("order-1", products, 1708560000L, "Author");
    }

    @Test
    void testSaveNewPaymentReturnsSavedPayment() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);

        Payment result = paymentRepository.save(payment);

        assertEquals("pay-1", result.getId());
    }

    @Test
    void testSaveNewPaymentCanBeFoundById() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        paymentRepository.save(payment);

        Payment found = paymentRepository.findById("pay-1");
        assertEquals("pay-1", found.getId());
    }

    @Test
    void testSaveUpdatesExistingPaymentById() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        paymentRepository.save(payment);

        payment.setStatus("SUCCESS");
        paymentRepository.save(payment);

        Payment found = paymentRepository.findById("pay-1");
        assertEquals("SUCCESS", found.getStatus());
    }

    @Test
    void testSaveUpdateDoesNotCreateDuplicate() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        paymentRepository.save(payment);
        paymentRepository.save(payment);

        assertEquals(1, paymentRepository.findAll().size());
    }

    @Test
    void testFindByIdReturnsCorrectPayment() {
        Map<String, String> data1 = new HashMap<>();
        Map<String, String> data2 = new HashMap<>();
        paymentRepository.save(new Payment("pay-1", "VOUCHER_CODE", data1, order));
        paymentRepository.save(new Payment("pay-2", "BANK_TRANSFER", data2, order));

        Payment found = paymentRepository.findById("pay-2");
        assertEquals("pay-2", found.getId());
        assertEquals("BANK_TRANSFER", found.getMethod());
    }

    @Test
    void testFindByIdReturnsNullIfNotFound() {
        Payment found = paymentRepository.findById("nonexistent-id");
        assertNull(found);
    }

    @Test
    void testFindAllReturnsAllSavedPayments() {
        Map<String, String> data1 = new HashMap<>();
        Map<String, String> data2 = new HashMap<>();
        paymentRepository.save(new Payment("pay-1", "VOUCHER_CODE", data1, order));
        paymentRepository.save(new Payment("pay-2", "BANK_TRANSFER", data2, order));

        List<Payment> all = paymentRepository.findAll();
        assertEquals(2, all.size());
    }

    @Test
    void testFindAllReturnsEmptyListWhenNoPayments() {
        List<Payment> all = paymentRepository.findAll();
        assertTrue(all.isEmpty());
    }
}

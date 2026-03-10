package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Order order;

    @BeforeEach
    void setUp(){
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("p1");
        product.setProductName("Product 1");
        product.setProductQuantity(2);
        products.add(product);
        order = new Order("order-1", products, 1708560000L, "TestAuthor");
    }

    @Test
    void testCreatePaymentHasCorrectId() {
        Map<String, String> data = new HashMap<>();
        data.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        assertEquals("pay-1", payment.getId());
    }

    @Test
    void testCreatePaymentHasCorrectMethod() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        assertEquals("VOUCHER_CODE", payment.getMethod());
    }

    @Test
    void testCreatePaymentDefaultStatusIsPending() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testCreatePaymentHasCorrectPaymentData() {
        Map<String, String> data = new HashMap<>();
        data.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        assertEquals(data, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentHasCorrectOrder() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        assertEquals(order, payment.getOrder());
    }

    @Test
    void testSetStatusToSuccess() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToPending() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        payment.setStatus("SUCCESS");
        payment.setStatus("PENDING");
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithNullIdThrowsException() {
        Map<String, String> data = new HashMap<>();
        assertThrows(IllegalArgumentException.class,
                () -> new Payment(null, "VOUCHER_CODE", data, order));
    }

    @Test
    void testCreatePaymentWithNullMethodThrowsException() {
        Map<String, String> data = new HashMap<>();
        assertThrows(IllegalArgumentException.class,
                () -> new Payment("pay-1", null, data, order));
    }

    @Test
    void testCreatePaymentWithNullPaymentDataThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Payment("pay-1", "VOUCHER_CODE", null, order));
    }

    @Test
    void testCreatePaymentWithNullOrderThrowsException() {
        Map<String, String> data = new HashMap<>();
        assertThrows(IllegalArgumentException.class,
                () -> new Payment("pay-1", "VOUCHER_CODE", data, null));
    }

    @Test
    void testSetStatusWithInvalidStatusThrowsException() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        assertThrows(IllegalArgumentException.class,
                () -> payment.setStatus("INVALID_STATUS"));
    }

    @Test
    void testSetStatusWithNullThrowsException() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        assertThrows(IllegalArgumentException.class,
                () -> payment.setStatus(null));
    }


}

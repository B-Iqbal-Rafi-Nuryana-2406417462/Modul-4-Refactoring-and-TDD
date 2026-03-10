package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.*;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order order;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("p-1");
        product.setProductName("Product 1");
        product.setProductQuantity(2);
        products.add(product);
        order = new Order("order-1", products, 1708560000L, "Author");
    }

    @Test
    void testAddPaymentSavesToRepositoryAndReturnsPayment() {
        Map<String, String> data = new HashMap<>();
        data.put("voucherCode", "ESHOP1234ABC5678");
        when(paymentRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Payment result = paymentService.addPayment(order, "VOUCHER_CODE", data);

        assertNotNull(result);
        assertEquals("VOUCHER_CODE", result.getMethod());
        verify(paymentRepository, times(1)).save(any());
    }

    @Test
    void testSetStatusSuccessAlsoSetsOrderStatusToSuccess() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        when(paymentRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Payment result = paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testSetStatusRejectedAlsoSetsOrderStatusToFailed() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        when(paymentRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Payment result = paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", result.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    void testSetStatusWithInvalidStatusThrowsException() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.setStatus(payment, "INVALID"));
    }

    @Test
    void testGetPaymentReturnsCorrectPayment() {
        Map<String, String> data = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", data, order);
        when(paymentRepository.findById("pay-1")).thenReturn(payment);

        Payment result = paymentService.getPayment("pay-1");

        assertEquals("pay-1", result.getId());
    }

    @Test
    void testGetPaymentReturnsNullIfNotFound() {
        when(paymentRepository.findById("nonexistent")).thenReturn(null);

        Payment result = paymentService.getPayment("nonexistent");

        assertNull(result);
    }

    @Test
    void testGetAllPaymentsReturnsAllPayments() {
        Map<String, String> data1 = new HashMap<>();
        Map<String, String> data2 = new HashMap<>();
        List<Payment> payments = Arrays.asList(
                new Payment("pay-1", "VOUCHER_CODE", data1, order),
                new Payment("pay-2", "BANK_TRANSFER", data2, order)
        );
        when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(2, result.size());
    }

    @Test
    void testGetAllPaymentsReturnsEmptyListWhenNone() {
        when(paymentRepository.findAll()).thenReturn(Collections.emptyList());

        List<Payment> result = paymentService.getAllPayments();

        assertTrue(result.isEmpty());
    }

}

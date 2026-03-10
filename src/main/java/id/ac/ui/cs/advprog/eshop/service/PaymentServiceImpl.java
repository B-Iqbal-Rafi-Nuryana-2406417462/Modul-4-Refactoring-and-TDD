package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(final PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(final Order order, final String method, final Map<String, String> paymentData) {
        final String id = UUID.randomUUID().toString();
        final Payment payment = new Payment(id, method, paymentData, order);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment setStatus(final Payment payment, final String status) {
        payment.setStatus(status);

        if (PaymentStatus.SUCCESS.getValue().equals(status)) {
            payment.getOrder().setStatus(PaymentStatus.SUCCESS.getValue());
        } else if (PaymentStatus.REJECTED.getValue().equals(status)) {
            payment.getOrder().setStatus(OrderStatus.FAILED.getValue());
        }

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(final String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}

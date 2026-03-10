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
        String status = determineStatus(method,paymentData);
        payment.setStatus(status);
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

    private String determineStatus(String method, Map<String, String> paymentData) {
        if ("VOUCHER_CODE".equals(method)) {
            return validateVoucherCode(paymentData.get("voucherCode"));
        }
        if ("BANK_TRANSFER".equals(method)) {
            // TODO : Implement bank transfer
        }
        return "PENDING";
    }

    private String validateVoucherCode(String code) {
        if (code == null || code.length() != 16) return "REJECTED";
        if (!code.startsWith("ESHOP")) return "REJECTED";
        long digitCount = code.chars().filter(Character::isDigit).count();
        if (digitCount != 8) return "REJECTED";
        return "SUCCESS";
    }
}

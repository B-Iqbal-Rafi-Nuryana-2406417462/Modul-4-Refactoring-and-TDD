package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
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
        payment.setStatus(determineStatus(method, paymentData));
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

    private String determineStatus(final String method, final Map<String, String> paymentData) {
        if (PaymentMethod.VOUCHER_CODE.getValue().equals(method)) {
            return validateVoucherCode(paymentData.get("voucherCode"));
        }
        if (PaymentMethod.BANK_TRANSFER.getValue().equals(method)) {
            return validateBankTransfer(paymentData);
        }
        return PaymentStatus.PENDING.getValue();
    }

    private String validateVoucherCode(final String code) {
        if (code == null || code.length() != 16) {
            return PaymentStatus.REJECTED.getValue();
        }
        if (!code.startsWith("ESHOP")) {
            return PaymentStatus.REJECTED.getValue();
        }
        final long digitCount = code.chars().filter(Character::isDigit).count();
        if (digitCount != 8) {
            return PaymentStatus.REJECTED.getValue();
        }
        return PaymentStatus.SUCCESS.getValue();
    }

    private String validateBankTransfer(final Map<String, String> data) {
        if (isNullOrEmpty(data.get("bankName"))) {
            return PaymentStatus.REJECTED.getValue();
        }
        if (isNullOrEmpty(data.get("referenceCode"))) {
            return PaymentStatus.REJECTED.getValue();
        }
        return PaymentStatus.SUCCESS.getValue();
    }

    private boolean isNullOrEmpty(final String value) {
        return value == null || value.isEmpty();
    }
}

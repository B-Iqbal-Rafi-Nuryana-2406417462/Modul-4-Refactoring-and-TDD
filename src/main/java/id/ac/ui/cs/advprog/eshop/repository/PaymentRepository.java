package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PaymentRepository {
    private final Map<String, Payment> paymentData = new HashMap<>();

    public Payment save(final Payment payment){
        paymentData.put(payment.getId(),payment);
        return payment;
    }

    public Payment findById(final String id){
        return paymentData.get(id);
    }

    public List<Payment> findAll(){
        return new ArrayList<>(paymentData.values());
    }
}

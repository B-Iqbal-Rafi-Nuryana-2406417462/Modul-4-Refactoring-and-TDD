package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Payment {
    private static final Set<String> VALID_STATUSES = Set.of("PENDING", "SUCCESS", "REJECTED");

    private final String id;
    private final String method;
    private String status;
    private final Map<String, String> paymentData;
    private final Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        if (id == null) throw new IllegalArgumentException("id cannot be null");
        if (method == null) throw new IllegalArgumentException("method cannot be null");
        if (paymentData == null) throw new IllegalArgumentException("paymentData cannot be null");
        if (order == null) throw new IllegalArgumentException("order cannot be null");

        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.order = order;
        this.status = "PENDING";
    }

    public void setStatus(String status) {
        if (status == null || !VALID_STATUSES.contains(status)) {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }
        this.status = status;
    }
}


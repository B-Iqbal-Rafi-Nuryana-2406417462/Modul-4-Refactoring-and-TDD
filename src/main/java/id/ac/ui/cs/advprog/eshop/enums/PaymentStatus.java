package id.ac.ui.cs.advprog.eshop.enums;

public enum PaymentStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    REJECTED("REJECTED");

    private final String value;

    PaymentStatus(String value) { this.value = value; }

    public String getValue() { return value; }

    public static boolean contains(String status) {
        for (PaymentStatus ps : values()) {
            if (ps.value.equals(status)) return true;
        }
        return false;
    }

}

package id.ac.ui.cs.advprog.eshop.enums;

public enum PaymentStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    REJECTED("REJECTED");

    private final String value;

    PaymentStatus(final String value) { this.value = value; }

    public String getValue() { return value; }

    public static boolean contains(final String status) {
        for (final PaymentStatus ps : values()) {
            if (ps.value.equals(status)) {
                return true;
            }
        }
        return false;
    }

}

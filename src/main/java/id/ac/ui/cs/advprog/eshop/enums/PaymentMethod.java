package id.ac.ui.cs.advprog.eshop.enums;

public enum PaymentMethod {
    VOUCHER_CODE("VOUCHER_CODE"),
    BANK_TRANSFER("BANK_TRANSFER");

    private final String value;

    PaymentMethod(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


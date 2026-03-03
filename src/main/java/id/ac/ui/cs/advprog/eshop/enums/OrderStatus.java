package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    WAITING_PAYMENT("WAITING_PAYMENT"),
    FAILED("FAILED"),
    SUCCESS("SUCCESS"),
    CANCELLED("CANCELLED");

    private final String value;

    OrderStatus(final String value) {
        this.value = value;
    }

    public static boolean contains(final String param){
        boolean isContains = false;
        for (final OrderStatus orderStatus : values()){
            if (orderStatus.name().equals(param)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }
}

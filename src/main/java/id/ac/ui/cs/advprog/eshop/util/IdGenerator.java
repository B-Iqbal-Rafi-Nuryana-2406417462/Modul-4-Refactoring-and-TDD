package id.ac.ui.cs.advprog.eshop.util;

public final class IdGenerator {

    private IdGenerator() {
        // Private constructor to prevent instantiation
    }

    public static String generateId() {
        return java.util.UUID.randomUUID().toString();
    }
}

package id.ac.ui.cs.advprog.eshop.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public final class IteratorToList {
    private IteratorToList() {
        // Private constructor to prevent instantiation
    }

    public static <T> List<T> convertIteratorToList(final Iterator<T> iterator) {
        final List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
}

package kata.supermarket;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Basket {
    private final List<Item> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        items.add(item);
    }

    public List<Item> items() {
        return Collections.unmodifiableList(items);
    }
}

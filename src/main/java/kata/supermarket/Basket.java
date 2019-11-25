package kata.supermarket;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Basket {
    private final List<Item> items;
    private final Map<BaseProduct, AtomicLong> itemsCount;

    public Basket() {
        this.items = new ArrayList<>();
        this.itemsCount = new HashMap<>();
    }

    public void add(final Item item) {
        items.add(item);
        itemsCount.putIfAbsent(item.product(), new AtomicLong(0l));
        itemsCount.get(item.product()).incrementAndGet();
    }

    public List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public Map<BaseProduct, AtomicLong> itemsCount() {
        return itemsCount;
    }
}

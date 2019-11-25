package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Basket {
    private final List<Item> items;
    private final Map<BaseProduct, AtomicInteger> itemsCount;

    public Basket() {
        this.items = new ArrayList<>();
        this.itemsCount = new HashMap<>();
    }

    public void add(final Item item) {
        items.add(item);
        itemsCount.putIfAbsent(item.product(), new AtomicInteger(0));
        itemsCount.get(item).incrementAndGet();
    }

    public List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public Map<BaseProduct, AtomicInteger> itemsCount() {
        return itemsCount;
    }
}

package kata.supermarket.discount;

import kata.supermarket.BaseProduct;
import kata.supermarket.Item;

import java.math.BigDecimal;

public abstract class DiscountedItem implements Item {
    private final Item item;

    DiscountedItem(Item item) {
        this.item = item;
    }

    @Override
    public BigDecimal price() {
        return item.price();
    }

    @Override
    public BaseProduct product() {
        return item.product();
    }

    abstract DiscountType getDiscountType();
}

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscountedItem that = (DiscountedItem) o;

        return item != null ? item.product().equals(that.item.product()) : that.item == null;
    }

    @Override
    public int hashCode() {
        return item != null ? item.product().hashCode() : 0;
    }

    @Override
    public BaseProduct product() {
        return item.product();
    }

    public boolean isDisCountApplicable() {
        return true;
    }

    public abstract DiscountedType getDiscountType();
}

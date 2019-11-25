package kata.supermarket.strategy;

import kata.supermarket.BaseProduct;
import kata.supermarket.discount.DiscountedItem;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public interface DiscountStrategy {
    BigDecimal execute(Map<DiscountedItem, AtomicLong> products);
}

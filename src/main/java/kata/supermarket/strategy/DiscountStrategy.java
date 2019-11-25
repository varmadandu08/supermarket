package kata.supermarket.strategy;

import kata.supermarket.BaseProduct;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public interface DiscountStrategy {
    BigDecimal execute(Map<BaseProduct, AtomicLong> products);
}

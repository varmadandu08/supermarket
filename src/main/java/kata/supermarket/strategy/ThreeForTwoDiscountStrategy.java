package kata.supermarket.strategy;

import kata.supermarket.BaseProduct;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ThreeForTwoDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal execute(Map<BaseProduct, AtomicLong> products) {
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }
}

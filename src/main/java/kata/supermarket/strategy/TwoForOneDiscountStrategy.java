package kata.supermarket.strategy;

import kata.supermarket.BaseProduct;
import kata.supermarket.discount.DiscountedItem;
import kata.supermarket.discount.DiscountedType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class TwoForOneDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal execute(Map<DiscountedItem, AtomicLong> allDiscontedProducts) {
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }
}

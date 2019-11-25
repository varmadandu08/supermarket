package kata.supermarket.strategy;

import kata.supermarket.BaseProduct;
import kata.supermarket.discount.DiscountedItem;
import kata.supermarket.discount.DiscountedType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public interface DiscountStrategy {
    BigDecimal execute(Map<DiscountedItem, AtomicLong> products);

    default Map<DiscountedItem, AtomicLong> getProductsHavingDiscountType(Map<DiscountedItem, AtomicLong> allDiscountedProducts, DiscountedType discountedType) {
        return allDiscountedProducts.entrySet().stream()
                .filter(entry -> discountedType == entry.getKey().getDiscountType())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

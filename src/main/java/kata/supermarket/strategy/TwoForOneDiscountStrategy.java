package kata.supermarket.strategy;

import kata.supermarket.discount.DiscountedItem;
import kata.supermarket.discount.DiscountedType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


public class TwoForOneDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal execute(Map<DiscountedItem, AtomicLong> allDiscountedProducts) {
        BigDecimal twoForOneDiscountAmount = BigDecimal.ZERO;

        Map<DiscountedItem, AtomicLong> twoForOneDiscountedProducts = getTwoOneDiscountedProducts(allDiscountedProducts);

        for(DiscountedItem discountedItem: twoForOneDiscountedProducts.keySet()) {
            BigDecimal productFullPricePerUnit = discountedItem.price();
            long productQuantity = twoForOneDiscountedProducts.get(discountedItem).get();
            long factor = productQuantity / DiscountedType.TWO_FOR_ONE.getMinQty();
            BigDecimal itemDiscount = productFullPricePerUnit.multiply(BigDecimal.valueOf(factor));
            twoForOneDiscountAmount = twoForOneDiscountAmount.add(itemDiscount);
        }
        return twoForOneDiscountAmount;
    }

    private Map<DiscountedItem, AtomicLong> getTwoOneDiscountedProducts(Map<DiscountedItem, AtomicLong> allDiscountedProducts) {
        return allDiscountedProducts.entrySet().stream()
                .filter(entry -> DiscountedType.TWO_FOR_ONE == entry.getKey().getDiscountType())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

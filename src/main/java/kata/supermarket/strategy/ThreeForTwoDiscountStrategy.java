package kata.supermarket.strategy;

import kata.supermarket.discount.DiscountedItem;
import kata.supermarket.discount.DiscountedType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ThreeForTwoDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal execute(Map<DiscountedItem, AtomicLong> allDiscountedProducts) {
        BigDecimal threeForTwoDiscountAmount = BigDecimal.ZERO;

        Map<DiscountedItem, AtomicLong> threeForTwoDiscountedProducts = getProductsHavingDiscountType(allDiscountedProducts, DiscountedType.THREE_FOR_TWO);

        for(DiscountedItem discountedItem: threeForTwoDiscountedProducts.keySet()) {
            BigDecimal productFullPricePerUnit = discountedItem.price();
            long productQuantity = threeForTwoDiscountedProducts.get(discountedItem).get();
            long factor = productQuantity / DiscountedType.THREE_FOR_TWO.getMinQty();
            BigDecimal itemDiscount = productFullPricePerUnit.multiply(BigDecimal.valueOf(factor));
            threeForTwoDiscountAmount = threeForTwoDiscountAmount.add(itemDiscount);
        }
        return threeForTwoDiscountAmount;
    }

}

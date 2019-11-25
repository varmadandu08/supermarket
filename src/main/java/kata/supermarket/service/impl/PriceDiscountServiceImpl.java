package kata.supermarket.service.impl;

import kata.supermarket.BaseProduct;
import kata.supermarket.Basket;
import kata.supermarket.Item;
import kata.supermarket.service.PriceDiscountService;
import kata.supermarket.strategy.ThreeForTwoDiscountStrategy;
import kata.supermarket.strategy.TwoForOneDiscountStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class PriceDiscountServiceImpl implements PriceDiscountService {

    private final Basket basket;

    public PriceDiscountServiceImpl(Basket basket) {
        this.basket = basket;
    }

    @Override
    public BigDecimal calculateDiscount() {
        BigDecimal totalDiscount = BigDecimal.ZERO;

        Map<BaseProduct, AtomicLong> discountedProducts = getDiscountedProductsWithCount();

        BigDecimal twoForOneDiscount = new TwoForOneDiscountStrategy().execute(discountedProducts);
        BigDecimal threeForTwoDiscount = new ThreeForTwoDiscountStrategy().execute(discountedProducts);

        totalDiscount = totalDiscount.add(twoForOneDiscount).add(threeForTwoDiscount);
        return totalDiscount.setScale(2, RoundingMode.HALF_UP);

    }

    private Map<BaseProduct, AtomicLong> getDiscountedProductsWithCount() {
        Map<BaseProduct, AtomicLong> discountedProducts = new HashMap<>();
        basket.items().stream()
                .filter(Item::isDisCountApplicable)
                .forEach(item -> {
                    discountedProducts.putIfAbsent(item.product(), new AtomicLong(0l));
                    discountedProducts.get(item.product()).incrementAndGet();
                });
        return discountedProducts;
    }
}

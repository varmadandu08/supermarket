package kata.supermarket.service.impl;

import kata.supermarket.Basket;
import kata.supermarket.service.PriceDiscountService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceDiscountServiceImpl implements PriceDiscountService {

    private final Basket basket;

    public PriceDiscountServiceImpl(Basket basket) {
        this.basket = basket;
    }

    @Override
    public BigDecimal calculateDiscount() {
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }
}

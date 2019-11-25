package kata.supermarket.service.impl;

import kata.supermarket.Basket;
import kata.supermarket.Item;
import kata.supermarket.service.PriceCalculationService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCalculationServiceImpl implements PriceCalculationService {

    private final Basket basket;

    public PriceCalculationServiceImpl(Basket basket) {
        this.basket = basket;
    }

    @Override
    public BigDecimal calculatePrice() {
        return getPrice(this.basket);
    }

    private BigDecimal getPrice(Basket basket) {
        return basket.items().stream().map(Item::price)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }
}

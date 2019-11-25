package kata.supermarket.service.impl;

import kata.supermarket.Basket;
import kata.supermarket.service.CheckoutService;
import kata.supermarket.service.PriceCalculationService;
import kata.supermarket.service.PriceDiscountService;

import java.math.BigDecimal;

public class CheckoutServiceImpl implements CheckoutService {

    private final Basket basket;
    private final PriceCalculationService priceCalculationService;
    private final PriceDiscountService priceDiscountService;


    public CheckoutServiceImpl(Basket basket) {
        this.basket = basket;
        this.priceCalculationService = new PriceCalculationServiceImpl(basket);
        this.priceDiscountService = new PriceDiscountServiceImpl(basket);
    }

    @Override
    public BigDecimal calculateTotal() {
        return priceCalculationService.calculatePrice().subtract(priceDiscountService.calculateDiscount());
    }
}

package kata.supermarket.service;

import kata.supermarket.Basket;
import kata.supermarket.Item;
import kata.supermarket.service.impl.PriceCalculationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static kata.supermarket.TestFixtures.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceCalculationServiceTest {

    @DisplayName("PriceCalculationService provides correct calculations when basket containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void priceCalculationServiceProvidesCorrectPriceCalculations(String description, BigDecimal expectedFullPrice, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(expectedFullPrice, new PriceCalculationServiceImpl(basket).calculatePrice());
    }

    static Stream<Arguments> priceCalculationServiceProvidesCorrectPriceCalculations() {
        return Stream.of(
                noItems(new BigDecimal("0.00")),
                aSingleItemPricedPerUnit(new BigDecimal("1.00")),
                multipleItemsPricedPerUnit(new BigDecimal("3.00")),
                aSingleItemPricedByWeight(new BigDecimal("0.75")),
                multipleItemsPricedByWeight(new BigDecimal("1.55"))
        );
    }
}
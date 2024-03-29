package kata.supermarket.service;

import kata.supermarket.Basket;
import kata.supermarket.Item;
import kata.supermarket.service.impl.PriceDiscountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static kata.supermarket.TestFixtures.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceDiscountServiceTest {

    @DisplayName("Checkout service provides correct total when basket containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void priceDiscountServiceProvidesCorrectDiscount(String description, BigDecimal expectedDiscount, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(expectedDiscount, new PriceDiscountServiceImpl(basket).calculateDiscount());
    }

    static Stream<Arguments> priceDiscountServiceProvidesCorrectDiscount() {
        return Stream.of(
                noItems(new BigDecimal("0.00")),
                aSingleItemPricedPerUnit(new BigDecimal("0.00")),
                multipleItemsPricedPerUnit(new BigDecimal("0.00")),
                aSingleItemPricedByWeight(new BigDecimal("0.00")),
                multipleItemsPricedByWeight(new BigDecimal("0.00")),

                aSingleItemPricedPerUnit_With_TwoForOneDiscount(new BigDecimal("0.00")),
                multipleItemsPricedPerUnit_With_2_individual_TwoForOneDiscount(new BigDecimal("0.00")),
                multipleItemsPricedPerUnit_With_2_pairs_of_TwoForOneDiscount(new BigDecimal("3.00")), // 1.00 for Milk discount and 2.00 for digestives

                aSingleItemPricedPerUnit_With_ThreeForTwoDiscount(new BigDecimal("0.00")),
                multipleItemsPricedPerUnit_having_3_same_products_with_ThreeForTwoDiscount(new BigDecimal("1.00")),
                multipleItemsPricedPerUnit_having_4_same_products_with_ThreeForTwoDiscount(new BigDecimal("2.00"))
        );
    }


}
package kata.supermarket.service;

import kata.supermarket.*;
import kata.supermarket.discount.ThreeForTwoDiscountedItemByUnit;
import kata.supermarket.discount.TwoForOneDiscountedItemByUnit;
import kata.supermarket.service.impl.PriceDiscountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceDiscountServiceTest {

    @DisplayName("Checkout service provides correct total when basket containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void priceDiscountServiceProvidesCorrectDiscount(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), new PriceDiscountServiceImpl(basket).calculateDiscount());
    }

    static Stream<Arguments> priceDiscountServiceProvidesCorrectDiscount() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                aSingleItemPricedPerUnit_With_TwoForOneDiscount(),
                multipleItemsPricedPerUnit_With_2_individual_TwoForOneDiscount(),
                multipleItemsPricedPerUnit_With_2_pairs_of_TwoForOneDiscount(),
                aSingleItemPricedPerUnit_With_ThreeForTwoDiscount(),
                multipleItemsPricedPerUnit_having_3_same_products_with_ThreeForTwoDiscount(),
                multipleItemsPricedPerUnit_having_4_same_products_with_ThreeForTwoDiscount()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "0.00", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "0.00",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "0.00",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments multipleItemsPricedPerUnit_With_2_individual_TwoForOneDiscount() {
        return Arguments.of("multiple items priced per unit", "0.00",
                Arrays.asList(new TwoForOneDiscountedItemByUnit(aPackOfDigestivesProduct()), new TwoForOneDiscountedItemByUnit(aPintOfMilkProduct())));
    }

    private static Arguments multipleItemsPricedPerUnit_having_3_same_products_with_ThreeForTwoDiscount() {
        return Arguments.of("multiple items priced per unit", "1.00",
                Arrays.asList(new ThreeForTwoDiscountedItemByUnit(aPackOfDigestivesProduct())
                        , new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())
                        , new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())));
    }

    private static Arguments multipleItemsPricedPerUnit_having_4_same_products_with_ThreeForTwoDiscount() {
        return Arguments.of("multiple items priced per unit", "1.00",
                Arrays.asList(new ThreeForTwoDiscountedItemByUnit(aPackOfDigestivesProduct())
                        , new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())
                        , new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())
                        , new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())));
    }

    private static Arguments multipleItemsPricedPerUnit_With_2_pairs_of_TwoForOneDiscount() {
        return Arguments.of("multiple items priced per unit", "3.00",
                Arrays.asList(new TwoForOneDiscountedItemByUnit(aPackOfDigestivesProduct()), new TwoForOneDiscountedItemByUnit(aPackOfDigestivesProduct())
                                , new TwoForOneDiscountedItemByUnit(aPintOfMilkProduct()), new TwoForOneDiscountedItemByUnit(aPintOfMilkProduct())));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.00", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit_With_TwoForOneDiscount() {
        return Arguments.of("a single item priced per unit with 2 for 1 discount", "0.00", Collections.singleton(new TwoForOneDiscountedItemByUnit(aPintOfMilkProduct())));
    }

    private static Arguments aSingleItemPricedPerUnit_With_ThreeForTwoDiscount() {
        return Arguments.of("a single item priced per unit with 2 for 1 discount", "0.00", Collections.singleton(new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Product aPintOfMilkProduct() {
        return new Product(ProductType.MILK, new BigDecimal( "1.00"));
    }

    private static Item aPintOfMilk() {
        return aPintOfMilkProduct().oneOf();
    }

    private static Product aPackOfDigestivesProduct() {
        return new Product(ProductType.DIGESTIVES, new BigDecimal("2.00"));
    }

    private static Item aPackOfDigestives() {
        return aPackOfDigestivesProduct().oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(ProductType.SWEETS, new BigDecimal("3.00"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(ProductType.PICKANDMIX, new BigDecimal("4.00"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}
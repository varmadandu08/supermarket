package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its items correctly when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesCorrectItems(String description, int numberOfItems, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(numberOfItems, basket.items().size());
    }

    static Stream<Arguments> basketProvidesCorrectItems() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    @DisplayName("basket provides count of products correctly when containing...")
    @Test
    void basketProvidesCorrectItemsCount() {
        final Basket basket = new Basket();


        WeighedProduct sweets = aKiloOfAmericanSweets();
        basket.add(sweets.weighing(new BigDecimal(".25")));
        basket.add(sweets.weighing(new BigDecimal(".50")));
        basket.add(sweets.weighing(new BigDecimal("1.00")));

        WeighedProduct pickAndMix = aKiloOfPickAndMix();
        basket.add(pickAndMix.weighing(new BigDecimal(".50")));

        assertEquals(1l, basket.itemsCount().get(pickAndMix).get());
        assertEquals(3l, basket.itemsCount().get(sweets).get());
    }

        private static Arguments aSingleItemPricedByWeight() {
            return Arguments.of("a single weighed item", 1, Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", 2,
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedByWeightWithCount() {
        return Arguments.of("multiple weighed items", 1,
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", 2,
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments multipleItemsPricedPerUnitWithCount() {
        return Arguments.of("multiple items priced per unit", 1,
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", 1, Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", 0, Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product(ProductType.MILK, new BigDecimal( "0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(ProductType.DIGESTIVES, new BigDecimal("1.55")).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(ProductType.SWEETS, new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(ProductType.PICKANDMIX, new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}
package kata.supermarket;

import kata.supermarket.discount.ThreeForTwoDiscountedItemByUnit;
import kata.supermarket.discount.TwoForOneDiscountedItemByUnit;
import org.junit.jupiter.params.provider.Arguments;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

public class TestFixtures {
    public static Arguments aSingleItemPricedByWeight(BigDecimal expectedValue) {
        return Arguments.of("a single weighed item", expectedValue, Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    public static Arguments multipleItemsPricedByWeight(BigDecimal expectedValue) {
        return Arguments.of("multiple weighed items", expectedValue,
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    public static Arguments multipleItemsPricedPerUnit(BigDecimal expectedValue) {
        return Arguments.of("multiple items priced per unit", expectedValue,
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    public static Arguments multipleItemsPricedPerUnit_With_2_individual_TwoForOneDiscount(BigDecimal expectedValue) {
        return Arguments.of("multiple items priced per unit - 2 individual products with 2 for 1 products", expectedValue,
                Arrays.asList(new TwoForOneDiscountedItemByUnit(aPackOfDigestivesProduct()), new TwoForOneDiscountedItemByUnit(aPintOfMilkProduct())));
    }

    public static Arguments multipleItemsPricedPerUnit_having_3_same_products_with_ThreeForTwoDiscount(BigDecimal expectedValue) {
        return Arguments.of("multiple items priced per unit - 3 same products having 3 for 2 discount", expectedValue,
                Arrays.asList(new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())
                        , new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())
                        , new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())));
    }

    public static Arguments multipleItemsPricedPerUnit_having_4_same_products_with_ThreeForTwoDiscount(BigDecimal expectedValue) {
        return Arguments.of("multiple items priced per unit - 4 same products with 3 for 2 discount", expectedValue,
                Arrays.asList(new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())
                        , new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())
                        , new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())
                        , new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())));
    }

    public static Arguments multipleItemsPricedPerUnit_With_2_pairs_of_TwoForOneDiscount(BigDecimal expectedValue) {
        return Arguments.of("multiple items priced per unit with 2 pairs of products having 2 for 1 discount", expectedValue,
                Arrays.asList(new TwoForOneDiscountedItemByUnit(aPackOfDigestivesProduct()), new TwoForOneDiscountedItemByUnit(aPackOfDigestivesProduct())
                        , new TwoForOneDiscountedItemByUnit(aPintOfMilkProduct()), new TwoForOneDiscountedItemByUnit(aPintOfMilkProduct())));
    }

    public static Arguments aSingleItemPricedPerUnit(BigDecimal expectedValue) {
        return Arguments.of("a single item priced per unit", expectedValue, Collections.singleton(aPintOfMilk()));
    }

    public static Arguments aSingleItemPricedPerUnit_With_TwoForOneDiscount(BigDecimal expectedValue) {
        return Arguments.of("a single item priced per unit with 2 for 1 discount", expectedValue, Collections.singleton(new TwoForOneDiscountedItemByUnit(aPintOfMilkProduct())));
    }

    public static Arguments aSingleItemPricedPerUnit_With_ThreeForTwoDiscount(BigDecimal expectedValue) {
        return Arguments.of("a single item priced per unit with 3 for 2 discount", expectedValue, Collections.singleton(new ThreeForTwoDiscountedItemByUnit(aPintOfMilkProduct())));
    }

    public static Arguments noItems(BigDecimal expectedValue) {
        return Arguments.of("no items", expectedValue, Collections.emptyList());
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

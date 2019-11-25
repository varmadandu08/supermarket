package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WeighedProductTest {

    @ParameterizedTest
    @MethodSource
    void itemFromWeighedProductHasExpectedUnitPrice(String pricePerKilo, String weightInKilos, String expectedPrice) {
        final WeighedProduct weighedProduct = new WeighedProduct(ProductType.PICKANDMIX, new BigDecimal(pricePerKilo));
        final Item weighedItem = weighedProduct.weighing(new BigDecimal(weightInKilos));
        assertEquals(new BigDecimal(expectedPrice), weighedItem.price());
    }

    static Stream<Arguments> itemFromWeighedProductHasExpectedUnitPrice() {
        return Stream.of(
                Arguments.of("100.00", "1.00", "100.00"),
                Arguments.of("100.00", "0.33333", "33.33"),
                Arguments.of("100.00", "0.33335", "33.34"),
                Arguments.of("100.00", "0", "0.00")
        );
    }

    @Test
    @DisplayName("check for equality of weighted products when type is same, even though price is different")
    void testEquality_same_weighted_product_type() {
        WeighedProduct product1 = new WeighedProduct(ProductType.SWEETS, new BigDecimal(1.00));
        WeighedProduct product2 = new WeighedProduct(ProductType.SWEETS, new BigDecimal(2.00));
        assertEquals(product1, product2);
    }

    @Test
    @DisplayName("check to make sure two weighted products NOT equal when their types are different")
    void testEquality_diff_weighted_product_type() {
        WeighedProduct product1 = new WeighedProduct(ProductType.SWEETS, new BigDecimal(1.00));
        WeighedProduct product2 = new WeighedProduct(ProductType.PICKANDMIX, new BigDecimal(1.00));
        assertNotEquals(product1, product2);
    }

}
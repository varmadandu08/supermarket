package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new Product(ProductType.MILK, price).oneOf().price());
    }

    @Test
    @DisplayName("check for equality of products when type is same, even though price is different")
    void testEquality_same_product_type() {
        Product product1 = new Product(ProductType.MILK, new BigDecimal(1.00));
        Product product2 = new Product(ProductType.MILK, new BigDecimal(2.00));
        assertEquals(product1, product2);
    }

    @Test
    @DisplayName("check to make sure two products NOT equal when their types are different")
    void testEquality_diff_product_type() {
        Product product1 = new Product(ProductType.MILK, new BigDecimal(1.00));
        Product product2 = new Product(ProductType.DIGESTIVES, new BigDecimal(1.00));
        assertNotEquals(product1, product2);
    }

}
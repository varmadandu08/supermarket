package kata.supermarket;

import kata.supermarket.discount.DiscountedType;

import java.math.BigDecimal;

public interface Item {
    BigDecimal price();
    BaseProduct product();
    boolean isDisCountApplicable();
}

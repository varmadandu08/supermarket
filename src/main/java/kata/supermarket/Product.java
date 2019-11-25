package kata.supermarket;

import java.math.BigDecimal;

public class Product extends BaseProduct {

    private final BigDecimal pricePerUnit;

    public Product(final ProductType productType, final BigDecimal pricePerUnit) {
        super(productType);
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}

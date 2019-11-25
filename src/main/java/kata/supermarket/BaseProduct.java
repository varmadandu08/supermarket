package kata.supermarket;

import java.util.Objects;

public class BaseProduct {

    private final ProductType productType;

    public BaseProduct(final ProductType productType) {
        this.productType = productType;
    }

    protected ProductType productType() {
        return this.productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseProduct that = (BaseProduct) o;
        return productType == that.productType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType);
    }
}

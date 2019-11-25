package kata.supermarket;

public class BaseProduct {

    private final ProductType productType;

    public BaseProduct(final ProductType productType) {
        this.productType = productType;
    }

    protected ProductType productType() {
        return this.productType;
    }

}

package kata.supermarket.discount;

public enum DiscountedType {
    TWO_FOR_ONE(2),
    THREE_FOR_TWO(3);

    final int minQty;

    DiscountedType(int minQty) {
        this.minQty = minQty;
    }

    public int getMinQty() {
        return minQty;
    }
}

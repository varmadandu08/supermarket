package kata.supermarket.discount;

public enum DiscountType {
    TWO_FOR_ONE(2),
    THREE_FOR_TWO(3);

    final int minQty;

    DiscountType(int minQty) {
        this.minQty = minQty;
    }

    public int getMinQty() {
        return minQty;
    }
}

package kata.supermarket.discount;

import kata.supermarket.ItemByUnit;
import kata.supermarket.Product;

public class TwoForOneDiscountedItemByUnit extends DiscountedItem {

    public TwoForOneDiscountedItemByUnit(final Product product) {
        super(new ItemByUnit(product));
    }

    @Override
    public DiscountedType getDiscountType() {
        return DiscountedType.TWO_FOR_ONE;
    }
}

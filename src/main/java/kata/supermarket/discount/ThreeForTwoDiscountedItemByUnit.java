package kata.supermarket.discount;

import kata.supermarket.ItemByUnit;
import kata.supermarket.Product;

public class ThreeForTwoDiscountedItemByUnit extends DiscountedItem {

    ThreeForTwoDiscountedItemByUnit(final Product product) {
        super(new ItemByUnit(product));
    }

    @Override
    public DiscountedType getDiscountType() {
        return DiscountedType.TWO_FOR_ONE;
    }
}

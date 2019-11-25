package kata.supermarket.discount;

import kata.supermarket.ItemByUnit;
import kata.supermarket.Product;

public class TwoForOneDiscountedItemByUnit extends DiscountedItem {

    TwoForOneDiscountedItemByUnit(final Product product) {
        super(new ItemByUnit(product));
    }

    @Override
    DiscountedType getDiscountType() {
        return DiscountedType.TWO_FOR_ONE;
    }
}

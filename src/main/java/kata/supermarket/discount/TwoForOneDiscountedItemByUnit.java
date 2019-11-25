package kata.supermarket.discount;

import kata.supermarket.ItemByUnit;
import kata.supermarket.Product;

public class TwoForOneDiscountedItemByUnit extends DiscountedItem {

    TwoForOneDiscountedItemByUnit(final Product product) {
        super(new ItemByUnit(product));
    }

    @Override
    DiscountType getDiscountType() {
        return DiscountType.TWO_FOR_ONE;
    }
}

package model;

import java.math.BigDecimal;
import java.util.Objects;

// ItemOrder stores information about a purchase order for a particular item.
public final class ItemOrder {
    private final Item myItem;
    private final int myQuantity;

    public ItemOrder(final Item theItem, final int theQuantity)
    {
        myItem = Objects.requireNonNull(theItem, "myItem can't be null");
        myQuantity = theQuantity;
    }

    // Calculate the price with a given quantity.
    public BigDecimal calculateOrderTotal()
    {
        return myItem.calculateItemTotal(myQuantity);
    }

    // Get a reference to the Item in the order.
    public Item getItem()
    {
        return myItem;
    }
}
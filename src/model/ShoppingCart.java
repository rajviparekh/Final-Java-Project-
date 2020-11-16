package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// ShoppingCart stores information about the customer's overall purchase
public class ShoppingCart {
    // The shopping cart
    private Map<Item, BigDecimal> myShoppingCart;

    // Constructor that creates an empty shopping cart
    public ShoppingCart()
    {
        myShoppingCart =  new HashMap<Item, BigDecimal>();
    }


    public void add(final ItemOrder theOrder)
    {
        Objects.requireNonNull(theOrder, "theOrder can't be null!");
        myShoppingCart.put(theOrder.getItem(), theOrder.calculateOrderTotal());
    }

    // Calculates the shopping cart total cost
    public BigDecimal calculateTotal() {
        BigDecimal orderTotal = BigDecimal.ZERO;

        for (Item item : myShoppingCart.keySet())
        {
            final BigDecimal currentOrderPrice = myShoppingCart.get(item);
            orderTotal = orderTotal.add(currentOrderPrice);
        }

        return orderTotal.setScale(2, RoundingMode.HALF_EVEN);
    }

    // Removes all orders from the cart
    public void clear()
    {
        myShoppingCart =  new HashMap<Item, BigDecimal>();
    }
}
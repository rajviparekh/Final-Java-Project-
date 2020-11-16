package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
/*
 Item stores information about an individual item.
 */
public final class Item
{

    private final String product;

    private final BigDecimal price;

    public Item(final String theName, final BigDecimal thePrice) {
        product = Objects.requireNonNull(theName, "theName can't be null!");
        price = Objects.requireNonNull(thePrice, "thePrice cant' be null!");
    }


     //Calculate the cost for a given quantity of the Item.

    public BigDecimal calculateItemTotal(final int theQuantity) {
        BigDecimal itemTotal;
        itemTotal = price.multiply(new BigDecimal(theQuantity));
        return itemTotal;
    }

    //Representation of Item and price
    @Override
    public String toString() {
        final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("en","IN"));
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(product);
        stringBuilder.append(" :  ");
        stringBuilder.append(numberFormat.format(price));
        return stringBuilder.toString();
    }
}
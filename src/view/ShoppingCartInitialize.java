package view;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import model.Item;

public final class ShoppingCartInitialize {
    // Reads item information from a file and returns a List of Item objects
    private static List<Item> readItemsFromFile()
    {
        final List<Item> items = new LinkedList<>();
        File myObj = new File("C:\\Shopping-cart-app-master\\items.txt");

        try (Scanner input = new Scanner(myObj))
        {
            while (input.hasNextLine()) {
                final Scanner line = new Scanner(input.nextLine());
                line.useDelimiter(";");
                final String itemName = line.next();
                final BigDecimal itemPrice = line.nextBigDecimal();
                items.add(new Item(itemName, itemPrice));
                line.close();
            }
        } catch (final IOException e)
        {
            e.printStackTrace();
        }
        return items;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                new ShoppingCartGUI(readItemsFromFile());
            }
        });
    }
}
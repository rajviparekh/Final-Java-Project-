package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Item;
import model.ItemOrder;
import model.ShoppingCart;

// ShoppingFrame provides the user interface for a shopping cart program
public final class ShoppingCartGUI extends JFrame
{
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    private static final int TEXT_FIELD_WIDTH = 20;
    // The color for some elements in the GUI
    private static final Color COLOR_1 = new Color(135, 206, 235);
    private static final Color COLOR_2 = new Color(0, 0, 77);


    private final ShoppingCart myItems;
    private final JTextField myTotal;

    private final List<JTextField> myQuantities;

    // Initializes the shopping cart GUI
    public ShoppingCartGUI(final List<Item> theItems)
    {
        // create frame and order list
        myItems = new ShoppingCart();

        // set up text field with order total
        myTotal = new JTextField("0", TEXT_FIELD_WIDTH);

        myQuantities = new LinkedList<>();

        setupGUI(theItems);
    }

    // Setup the various parts of the GUI
    private void setupGUI(final List<Item> theItems)
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(headingPanel(), BorderLayout.NORTH);
        add(makeItemsPanel(theItems), BorderLayout.CENTER);
        add(totalPanel(), BorderLayout.SOUTH);

        pack();

        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                SCREEN_SIZE.height / 2 - getHeight() / 2);
        setVisible(true);
    }

    // Creates a panel for header
    private JPanel headingPanel()
    {
        myTotal.setEditable(false);
        myTotal.setDisabledTextColor(Color.BLACK);

        final JPanel p = new JPanel();
        p.setBackground(COLOR_2);

        final JLabel l = new JLabel("SHOPPING CART");
        l.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        l.setForeground(Color.WHITE);
        p.add(l);

        return p;
    }

    // Creates a panel to hold the specified list of items
    private JPanel makeItemsPanel(final List<Item> theItems)
    {
        final JPanel p = new JPanel(new GridLayout(theItems.size(), 1));

        for (final Item item : theItems)
        {
            addItem(item, p);
        }
        return p;
    }

    // Creates a panel for total and clear
    private JPanel totalPanel() {
        final JPanel p = new JPanel();
        p.setBackground(COLOR_2);

        final JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myItems.clear();
                for (final JTextField field : myQuantities) {
                    field.setText("");
                }
                updateTotal();
            }
        });
        final JLabel l = new JLabel("Order Total");
        l.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        l.setForeground(Color.WHITE);
        p.add(l);
        p.add(myTotal);
        p.add(clearButton);
        return p;
    }

    private void addItem(final Item theItem, final JPanel thePanel) {
        final JPanel sub = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sub.setBackground(COLOR_1);
        final JTextField quantity = new JTextField(3);
        myQuantities.add(quantity);
        quantity.setHorizontalAlignment(SwingConstants.CENTER);
        quantity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {
                quantity.transferFocus();
            }
        });
        quantity.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent theEvent) {
                updateItem(theItem, quantity);
            }
        });
        final JLabel l = new JLabel(theItem.toString());
        l.setForeground(COLOR_2);
        sub.add(l);
        thePanel.add(sub);

        final JPanel sub1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sub1.setBackground(COLOR_1);
        sub1.add(quantity);
        thePanel.add(sub1);
    }

    private void updateItem(final Item theItem, final JTextField theQuantity) {
        final String text = theQuantity.getText().trim();
        int number;
        try {
            number = Integer.parseInt(text);
            if (number < 0) {
                throw new NumberFormatException();
            }
        } catch (final NumberFormatException e) {
            number = 0;
            theQuantity.setText("");
        }
        myItems.add(new ItemOrder(theItem, number));
        updateTotal();
    }

    // Updates the total displayed in the window
    private void updateTotal() {
        final double total = myItems.calculateTotal().doubleValue();
        myTotal.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(total));
    }
}
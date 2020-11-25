Description


This project is about writing a set of Java classes for a shopping cart. A Graphical User Interface is provided (GUI) which provides the view to the program. 
The entire project was programmed and implemented on IntelliJ Idea which is an IDE for Java. Having a user-friendly and simple design, the shopping cart takes the quantity input from the user for each item and calculates and displays the total bill amount to the user. The project consists of two packages, the model package, which moulds the entire shopping cart model and a view package which handles the entire frontend and visual part of the project.
The model package consists of three classes: Item.java, ItemOrder.java and ShoppingCart.java. Item class initializes each item, calculates the total price for each item for the quantity given by the user and converts the total price to a string, using NumberFormat class, which displays the price with the Indian Rupee symbol and adds commas at the appropriate places. It then merges the item description and price using the Stringbuilder class. The ItemOrder class, stores information about the quantity entered by the user, calls the function to calculate the quantity and returns a reference to the Item stored. 
The ShoppingCart class stores information about the overall purchase. The information about each item and its quantity is stored altogether in a hashmap. It calculates the total bill and stores the clear button function, which clears all values from the HashMap when the user presses the clear button.
The view package comprises of two classes: ShoppingCartGUI.java and ShoppingCartInitialize.java. The ShoppingCartGUI class contains the JFrame which gives the user a graphical interface. The ShoppingCartInitialize class, stores the main function which calls the entire program. It also extracts the item information from the .txt file using Scanner class and useDelimiter function.














package store_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Customer interface for interactions with products
 */
public class CustomerActions {
    private Scanner scnr = new Scanner(System.in);
    private ShoppingCart shoppingCart;
    private InventoryManager inventoryManager;

    public CustomerActions(StoreFront storeFront) {
        this.shoppingCart = storeFront.getShoppingCart();
        this.inventoryManager = storeFront.getInventoryManager();
    }
    /*
     * prints an interface for customer choices
     */
    public void printCustomerChoices() {
        System.out.println("1. View Products in inventory");
        System.out.println("2. Search for product by name");
        System.out.println("3. Add product to cart");
        System.out.println("4. Remove product from cart");
        System.out.println("5. View cart");
        System.out.println("6. Checkout");
        System.out.println("0. Exit");
        System.out.println("Choose an option: ");
    }
    /*
     * Branches to a customer interaction based on the customer choice
     * @param choice: user choice number
     */
    public void customerChoice(int choice) {
        List<ProductWithQuantity> itemList = inventoryManager.fetchAllProducts();
        List<ProductWithQuantity> cartList = shoppingCart.getAllCartItems();
        switch (choice)
        {
            case 1://view products in inventory
                for (int i = 0; i < itemList.size(); i++) {
                    System.out.println(itemList.get(i).toString());
                }
            break;

            case 2://search for products
                List <ProductWithQuantity> searchResults = new ArrayList<>();
                System.out.print("Please enter the name of item you are searching for: ");
                System.out.println();
                String userIn = scnr.nextLine();
                searchResults = inventoryManager.searchList(userIn);
                for (int i = 0; i < searchResults.size(); i++) {
                    System.out.println(searchResults.get(i));
                }
            break;

            case 3://add product to cart
                System.out.println("Choose the ID of the item you would like to purchase: ");
                for (int i = 0; i < itemList.size(); i++) {
                    System.out.println(itemList.get(i).toString());
                }
                userIn = scnr.nextLine();
                int chosenID = Integer.parseInt(userIn);
                ProductWithQuantity itemToAdd = itemList.get(chosenID - 1);//gets an item from the inventory list. NOT the instance used in the shopping cart
                System.out.println("You would like to purchase a " + itemToAdd.getSalableProduct().getName() + ".");
                System.out.println("Enter the amount you would like to purchase: ");
                userIn = scnr.nextLine();
                int numToPurchase = Integer.parseInt(userIn);
                if (numToPurchase > itemToAdd.getQuantity()) {
                    System.out.println("Not enough quantity in stock. Returning to menu.");
                    return;
                } else if (numToPurchase <= itemToAdd.getQuantity() && numToPurchase >= 0) {
                    ProductWithQuantity newItem = new ProductWithQuantity(itemToAdd.getSalableProduct(), numToPurchase);
                    shoppingCart.addProductWithQuantity(newItem);
                    inventoryManager.removeProductWithQuantity(numToPurchase, itemToAdd.getSalableProduct().getName());
                    System.out.println("Added " + numToPurchase + " " + itemToAdd.getSalableProduct().getName() + " to cart.");
                } else {
                    System.out.println("Invalid input. Returning to menu.");
                }
            break;

            case 4://remove product from cart
                String removedProductInput;
                int numItemsRemoved;
                System.out.println("Choose ID of item to be removed: ");//ID of product removed
                for (int i = 0; i < cartList.size(); i++) {
                    System.out.println(cartList.get(i).toString());
                }
                userIn= scnr.nextLine();
                int idOfItemRemoved = Integer.parseInt(userIn);
                System.out.println("Enter amount of item to be removed: ");//number of item removed
                removedProductInput = scnr.nextLine();
                numItemsRemoved = Integer.parseInt(removedProductInput);
                
                shoppingCart.removeProductWithQuantity(numItemsRemoved, idOfItemRemoved);
                inventoryManager.addProductWithQuantity(numItemsRemoved, idOfItemRemoved);//adds items removed from cart back into inventory
                System.out.println("Amount of item in cart and inventory updated.");
            break;

            case 5://view cart
                System.out.println("Items in cart:");
                for (int i = 0; i < cartList.size(); i++) {
                    System.out.println(cartList.get(i));
                }
            break;

            case 6: //checkout
                System.out.println("Checking out...");
                System.out.println("Total Cost:");
                double totalCost = 0;
                for (ProductWithQuantity item : cartList) {
                    totalCost += (item.getSalableProduct().getPrice() * item.getQuantity());
                }
                System.out.println("$" + totalCost);
            break;

            }

            
    }
}

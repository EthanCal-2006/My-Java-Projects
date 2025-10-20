package store_manager;
import java.io.IOException;
import java.util.Scanner;

/*
 * main App class
 */
public class App 
{
    /*
     * main method
     */
    public static void main( String[] args )
    {
        StoreFront storeFront = new StoreFront();
        
        Scanner scnr = new Scanner(System.in);
        CustomerActions customerActions = new CustomerActions(storeFront);
        StoreManagerActions storeManagerActions = new StoreManagerActions(storeFront);
        //ShoppingCart shoppingCart = new ShoppingCart(storeFront);
        InventoryManager inventoryManager = new InventoryManager(true);
        inventoryManager.initializeInventory();

        System.out.println("Welcome to the StoreFront Application!");
        System.out.println("Will you be using the app as a (1) customer, (2) store manager, or (3) as a server operator?");
        String line = scnr.nextLine();
        int userIn = Integer.parseInt(line);

        switch (userIn)
        {
            case 1:
            System.out.println("You are using this app as a customer.");
            customerActions.printCustomerChoices();
            line = scnr.nextLine();
            userIn = Integer.parseInt(line);
            while (userIn != 0)
            {
                customerActions.customerChoice(userIn);
                customerActions.printCustomerChoices();
                line = scnr.nextLine();
                userIn = Integer.parseInt(line);
            }
            System.out.println("Goodbye loyal customer!");
            break;

            case 2:
            System.out.println("You are using this app as a store manager.");
            storeManagerActions.printStoreManagerChoices();
            line = scnr.nextLine();
            userIn = Integer.parseInt(line);
            while (userIn != 0)
            {
                storeManagerActions.storeManagerChoice(userIn);
                storeManagerActions.printStoreManagerChoices();
                line = scnr.nextLine();
                userIn = Integer.parseInt(line);
            }
            System.out.println("Goodbye store manager!");
            break;

            case 3:
            Server server = new Server();
            try {
                server.start(6666); // Start server on port 6666
                server.cleanup();    // Cleanup after server stops
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
            
            default:
            System.out.println("Invalid input. try again.");
        }
        scnr.close();
    }
}

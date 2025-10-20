package store_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * class that handles store manager interface
 */
public class StoreManagerActions {
    InputUtilities iu = new InputUtilities();
    private Scanner scnr = new Scanner(System.in);
    private StoreFront storeFront;

    public StoreManagerActions(StoreFront storeFront) {
        this.storeFront = storeFront;
    }

    /*
     * prints choices for store manager to choose from
     */
    public void printStoreManagerChoices() {
        System.out.println("1. View Products");
        System.out.println("2. Search for product by name");
        System.out.println("3. Add product to inventory");
        System.out.println("4. Remove product from inventory");
        System.out.println("5. Update product to inventory");
        System.out.println("6. Save inventory to File");
        System.out.println("7. Load inventory from File");
        System.out.println("0. Exit");
        System.out.println("Choose an option: ");
    }
    /*
     * Branches to a store manager interaction based on the store manager choice
     * @param choice: user choice number
     */
    public void storeManagerChoice(int choice) {
        List<ProductWithQuantity> itemList = this.storeFront.getInventoryManager().fetchAllProducts();
        switch (choice)
        {
            case 1://view products
                for (int i = 0; i < itemList.size(); i++) {
                    System.out.println(itemList.get(i).toString());
                }
            break;

            case 2://search for products
                List <ProductWithQuantity> searchResults = new ArrayList<>();
                System.out.print("Please enter the name of item you are searching for: ");
                System.out.println();
                String userIn = scnr.nextLine();
                searchResults = this.storeFront.getInventoryManager().searchList(userIn);
                for (int i = 0; i < searchResults.size(); i++) {
                    System.out.println(searchResults.get(i));
                }
            break;

            case 3://add product
                SalableProduct salableProduct = new SalableProduct();
                System.out.println("Select item type's number: ");
                System.out.println("1. Guitar");
                System.out.println("2. Drums");
                System.out.println("3. Keys");
                String case3Input = scnr.nextLine();
                int managerChoice = Integer.parseInt(case3Input);
                
                switch (managerChoice)
                {
                case 1:
                salableProduct = new Guitar();
                break;
                case 2:
                salableProduct = new Drums();
                break;
                case 3:
                salableProduct = new Keys();
                break;
                }

                int case3ID = itemList.size() + 1;
                salableProduct.setId(case3ID);
                System.out.println("ID set to " + salableProduct.getId() + ".");

                System.out.println("Enter name: ");
                case3Input = scnr.nextLine();
                salableProduct.setName(case3Input);

                System.out.println("Enter description: ");
                case3Input = scnr.nextLine();
                salableProduct.setDescription(case3Input);

                System.out.println("Enter price: ");
                case3Input = scnr.nextLine();
                Double case3Price = Double.parseDouble(case3Input);
                salableProduct.setPrice(case3Price);

                System.out.println("Enter amount to add to inventory: ");
                case3Input = scnr.nextLine();
                int numItems = Integer.parseInt(case3Input);

                System.out.println("Enter date of manufacture in format (mm/dd/yyyy): ");
                case3Input = scnr.nextLine();
                salableProduct.setDateOfManufacture(case3Input);

                if (salableProduct instanceof Guitar) {
                    System.out.println("Enter guitar type: ");//setting guitar type
                    String guitarType = scnr.nextLine();
                    ((Guitar)salableProduct).setType(guitarType);

                    System.out.println("Enter number of strings: ");//setting strings of the guitar
                    case3Input = scnr.nextLine();
                    int numStrings = Integer.parseInt(case3Input);
                    String guitarString;
                    List<String> guitarStrings = new ArrayList<>();
                    for (int i = 0; i < numStrings; i++) {
                        System.out.print("Enter a string: ");
                        guitarString = scnr.nextLine();
                        guitarStrings.add(guitarString);
                    }
                    ((Guitar)salableProduct).setStrings(guitarStrings);

                } else if (salableProduct instanceof Drums) {
                    System.out.println("Enter drum type: ");//setting drum type
                    String drumType = scnr.nextLine();
                    ((Drums)salableProduct).setDrumType(drumType);

                    System.out.println("Enter drum diameter: ");//setting drum diameter
                    case3Input = scnr.nextLine();
                    double drumDiameter = Double.parseDouble(case3Input);
                    ((Drums)salableProduct).setDiameter(drumDiameter);
                    
                } else if (salableProduct instanceof Keys) {
                    System.out.println("Enter number of keys: ");//setting number of keys
                    case3Input = scnr.nextLine();
                    int numKeys = Integer.parseInt(case3Input);
                    ((Keys)salableProduct).setNumberOfKeys(numKeys);

                    System.out.println("Enter type of piano: ");//set keys type
                    String keysType = scnr.nextLine();
                    ((Keys)salableProduct).setType(keysType);
                }

                ProductWithQuantity newProductWithQuantity = new ProductWithQuantity(salableProduct, numItems);
                this.storeFront.getInventoryManager().addProductWithQuantity(newProductWithQuantity);
                System.out.println("Product added.");
            break;

            case 4://remove product
                String removedProductInput;
                int numItemsRemoved;
                System.out.println("Enter name of product to be removed: ");//name of product removed
                String nameOfItemRemoved = scnr.nextLine();
                System.out.println("Enter amount of item to be removed: ");//number of item removed
                removedProductInput = scnr.nextLine();
                numItemsRemoved = Integer.parseInt(removedProductInput);
                this.storeFront.getInventoryManager().removeProductWithQuantity(numItemsRemoved, nameOfItemRemoved);
                System.out.println("Amount of item in list updated.");
            break;

            case 5://update product
                String updateLine;
                int num = -1; //will be an identifier of correct productWithQuantity being referenced in the list
                System.out.println("Enter name of item you want to change: ");
                String updatedItem = scnr.nextLine();
                ProductWithQuantity itemToUpdate = null;
                for (int i = 0; i < itemList.size(); i++) {//itemList is accessible from this class for comparison purposes
                    if (updatedItem.equals(itemList.get(i).getSalableProduct().getName())) {//checks whole itemList to see if an item with the inputted name exists, then equates ith with itemToUpdate
                        itemToUpdate = itemList.get(i);
                        num = i;
                        break;
                    }
                    if (i == 3) {
                        System.out.println("Invalid name.");
                        return;
                    }
                }
                System.out.println("Choose the number of the option you would like to change: ");
                System.out.println("1. ID");
                System.out.println("2. name");
                System.out.println("3. description");
                System.out.println("4. price");
                System.out.println("5. date of manufacture");
                System.out.println("6. Instrument-specific attribute");
                String line = scnr.nextLine();
                int updateOption = Integer.parseInt(line);
                switch (updateOption)
                {
                    case 1:
                    System.out.println("Old ID was " + itemToUpdate.getSalableProduct().getId() + ". Enter new ID: ");
                    updateLine = scnr.nextLine();
                    int newID = Integer.parseInt(updateLine);
                    itemToUpdate.getSalableProduct().setId(newID);
                    break;

                    case 2:
                    System.out.println("Old name was " + itemToUpdate.getSalableProduct().getName() + ". Enter new name: ");
                    String newName = scnr.nextLine();
                    itemToUpdate.getSalableProduct().setName(newName);
                    break;

                    case 3:
                    System.out.println("Old description was " + itemToUpdate.getSalableProduct().getDescription() + ". Enter new description: ");
                    String newDescription = scnr.nextLine();
                    itemToUpdate.getSalableProduct().setDescription(newDescription);
                    break;

                    case 4:
                    System.out.println("Old price was " + itemToUpdate.getSalableProduct().getPrice() + ". Enter new price: ");
                    updateLine = scnr.nextLine();
                    double newPrice = Double.parseDouble(updateLine);
                    itemToUpdate.getSalableProduct().setPrice(newPrice);
                    break;

                    case 5:
                    System.out.println("Old date of manufacture was " + itemToUpdate.getSalableProduct().getDateOfManufacture() + ". Enter new date of manufacture in the same format: ");
                    updateLine = scnr.nextLine();
                    itemToUpdate.getSalableProduct().setDateOfManufacture(updateLine);
                    break;

                    case 6:
                        String instrumentType = itemToUpdate.getSalableProduct().getClass().getSimpleName();
                        switch (instrumentType)
                        {
                            case "Drums"://update a drum attribute
                                System.out.println("Choose the number of the option you would like to change:");
                                System.out.println("1. drum type");
                                System.out.println("2. drum diameter");
                                line = scnr.nextLine();
                                updateOption = Integer.parseInt(line);
                                switch (updateOption)
                                {
                                    case 1:
                                    System.out.println("Old drum type was " + ((Drums)itemToUpdate.getSalableProduct()).getDrumType() + ". Enter new drum type: ");
                                    String newDrumType = scnr.nextLine();
                                    ((Drums)itemToUpdate.getSalableProduct()).setDrumType(newDrumType);
                                    break;

                                    case 2:
                                    System.out.println("Old drum diameter was " + ((Drums)itemToUpdate.getSalableProduct()).getDiameter() + ". Enter new drum diameter: ");
                                    updateLine = scnr.nextLine();
                                    double newDiameter = Double.parseDouble(updateLine);
                                    ((Drums)itemToUpdate.getSalableProduct()).setDiameter(newDiameter);
                                    break;
                                }
                            break;

                            case "Guitar"://update a guitar attribute
                                System.out.println("Choose the number of the option you would like to change:");
                                System.out.println("1. guitar type");
                                System.out.println("2. guitar strings");
                                line = scnr.nextLine();
                                updateOption = Integer.parseInt(line);
                                switch (updateOption)
                                {
                                    case 1:
                                    System.out.println("Old guitar type was " + ((Guitar)itemToUpdate.getSalableProduct()).getType() + ". Enter new guitar type: ");
                                    String newGuitarType = scnr.nextLine();
                                    ((Guitar)itemToUpdate.getSalableProduct()).setType(newGuitarType);
                                    break;

                                    case 2:
                                    String guitarString;
                                    List<String> newGuitarStrings = new ArrayList<>();
                                    System.out.println("Old strings were " + ((Guitar)itemToUpdate.getSalableProduct()).getStrings() + ". Enter new number of guitar strings: ");
                                    updateLine = scnr.nextLine();
                                    double numGuitarStrings = Double.parseDouble(updateLine);
                                    for (int i = 0; i < numGuitarStrings; i++) {
                                        System.out.println("Enter a guitar string: ");
                                        guitarString = scnr.nextLine();
                                        newGuitarStrings.add(guitarString);
                                    }
                                    ((Guitar)itemToUpdate.getSalableProduct()).setStrings(newGuitarStrings);
                                break;
                            }
                            break;

                            case "Keys"://update Keys attribute
                                System.out.println("Choose the number of the option you would like to change:");
                                System.out.println("1. number of keys");
                                System.out.println("2. piano type");
                                line = scnr.nextLine();
                                updateOption = Integer.parseInt(line);
                                switch (updateOption)
                                {
                                    case 1:
                                    System.out.println("Old number of keys was " + ((Keys)itemToUpdate.getSalableProduct()).getNumberOfKeys() + ". Enter new number of keys: ");
                                    updateLine = scnr.nextLine();
                                    int newNumKeys = Integer.parseInt(updateLine);
                                    ((Keys)itemToUpdate.getSalableProduct()).setNumberOfKeys(newNumKeys);
                                    break;

                                    case 2:
                                    System.out.println("Old piano was " + ((Keys)itemToUpdate.getSalableProduct()).getType() + ". Enter new piano type: ");
                                    String newKeysType = scnr.nextLine();
                                    ((Keys)itemToUpdate.getSalableProduct()).setType(newKeysType);
                                    break;
                                }
                        break;
                        }
                    break;
                }

            break;

            case 6:
            String fileName = "InventoryItems";
            System.out.println("Saving inventory to " + fileName + "...");
            storeFront.saveInventoryToFile(fileName);
            break;

            case 7:
            fileName = "InventoryItems";
            System.out.println("Loading inventory items from " + fileName + "...");
            storeFront.loadInventoryFromFile(fileName);
            break;

            default:
            System.out.println("Invalid number. Please try again.");
            break;
        }
        
    }
    /*
     * separate method to close class scanner
     */
    public void closeScanner() {
        if (scnr != null) {
            scnr.close();
            scnr = null;
        }
    }
}

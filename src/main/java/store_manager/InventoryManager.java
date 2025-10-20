package store_manager;

import java.util.ArrayList;
import java.util.List;

/*
 * manages functions and I/O for inventory accessed by store manager
 */
public class InventoryManager {
    private List<ProductWithQuantity> itemList;
    /*
     * constructor
     * @param shoudInitialize: boolean that determines if inv should be initialized
     */
    public InventoryManager(Boolean shouldInitialize) {
        itemList = new ArrayList<ProductWithQuantity>();
        if (shouldInitialize) {
            initializeInventory();
        }
    }

    public InventoryManager() {
    }

    
    /*
     * initializes inv with base products
     */
    public void initializeInventory() {
        SalableProduct product1 = new Drums(1, "War drum", "deep as the ocean", 600, "bass", 12.0, "01/25/2006");
        SalableProduct product2 = new Drums(2, "Pearl", "Rock on", 850, "drum set", 10.0, "12/05/2007");
        SalableProduct product3 = new Keys(3, "Manhassett", "Beethoven would be proud", 500, 77, "Grand Piano", "05/25/2000");
        List<String> strings = new ArrayList<>();
        strings.add("E");
        strings.add("A");
        strings.add("D");
        strings.add("G");
        strings.add("B");
        strings.add("E");
        SalableProduct product4 = new Guitar(4, "Epiphone", "Plays like Cash", 400.0, "Acoustic", strings, "12/25/2000");
        this.itemList.add(new ProductWithQuantity(product1, 1));
        this.itemList.add(new ProductWithQuantity(product2, 2));
        this.itemList.add(new ProductWithQuantity(product3, 3));
        this.itemList.add(new ProductWithQuantity(product4, 4));
    }

    public List<ProductWithQuantity> fetchAllProducts() {
        return itemList;
    }

    public ProductWithQuantity getProductWithQuantity(int id) {
        for (ProductWithQuantity productWithQuantity : itemList) {
            if (productWithQuantity.getSalableProduct().getId() == id) {
                return productWithQuantity;
            }
        }
        return null;
    }

    /*
     * searches itemList for item with name entered
     * @param userIn: name of object that user is trying to find
     */
    public List<ProductWithQuantity> searchList(String userIn) {
        ArrayList<ProductWithQuantity> searchResults = new ArrayList<>();

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getSalableProduct().getName().contains(userIn)) {
                searchResults.add(itemList.get(i));
            }
        }
        return searchResults;
    }
    /*
     * adds a product to itemList
     * @param productWithQuantity: productWithQuantity to be appended to itemList
     */
    public void addProductWithQuantity(ProductWithQuantity productWithQuantity) {
        this.itemList.add(productWithQuantity);
    }

    public void addProductWithQuantity(int numItemsAdded, int idItemsAdded) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getSalableProduct().getId() == idItemsAdded) {
                itemList.get(i).setQuantity(itemList.get(i).getQuantity() + numItemsAdded);
                return;
            }
        }
    }
    /*
     * removes a product from the list
     * @param numItemsRemoved: number of item removed
     * @param nameOfItemRemoved: name of the item removed
     */
    public void removeProductWithQuantity(int numItemsRemoved, String nameOfItemRemoved) {
        int numItemsLeft;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getSalableProduct().getName().equals(nameOfItemRemoved)) {
                numItemsLeft = itemList.get(i).getQuantity() - numItemsRemoved;
                    if (numItemsLeft <= 0) {
                        numItemsLeft = 0;
                        itemList.get(i).setQuantity(numItemsLeft);
                    } else {
                        itemList.get(i).setQuantity(numItemsLeft);
                    }
            }
        }
    }

    public void removeProductWithQuantity(int numItemsRemoved, int idOfItemRemoved) {
        int numItemsLeft;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getSalableProduct().getId() == idOfItemRemoved) {
                numItemsLeft = itemList.get(i).getQuantity() - numItemsRemoved;
                    if (numItemsLeft <= 0) {
                        numItemsLeft = 0;
                        itemList.get(i).setQuantity(numItemsLeft);
                    } else {
                        itemList.get(i).setQuantity(numItemsLeft);
                    }
            }
        }
    }

    /*
     * updates a product in the list
     * @param itemToUpdate: productWithQuantity to be updated
     */
    public void updateProductWithQuantity(ProductWithQuantity itemToUpdate) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemToUpdate.equals(itemList.get(i))) {//checks whole itemList to see if an item with the inputted name exists, then equates ith with itemToUpdate
                itemList.set(i, itemToUpdate);
                i--;
                break;
            }
        }
    }
    public void setItemList(List<ProductWithQuantity> itemList) {
        this.itemList = itemList;
    }

    public List<ProductWithQuantity> getItemList() {
        return itemList;
    }
}

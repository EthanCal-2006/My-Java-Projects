package store_manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.JsonNode;

public class StoreFront {
    private InventoryManager inventoryManager;
    private ShoppingCart shoppingCart;
    /*
     * manages both inventory and shopping cart
     */
    public StoreFront() {
        inventoryManager = new InventoryManager(true);
        this.shoppingCart = new ShoppingCart();
        /*
         * serialize inv to a file
         * implemented in milestone 4
         */
    }

    public boolean saveInventoryToFile( String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper(); 

            String json = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this.inventoryManager);
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(json);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void loadInventoryFromFile(String fileName) {
        try {
            
            // mapper is the class from the Jackson library that converts JSON to objects
            ObjectMapper mapper = new ObjectMapper();
            // use standard Java file I/O to read the JSON file into a string
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);

            // StringBuilder is more efficient that concatenating strings with + operator
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line); // instead of json += line we use a StringBuilder
            }

            reader.close();
            String json = jsonBuilder.toString();


            try {
                InventoryManager dataInput = mapper.readValue(json, InventoryManager.class);
                this.inventoryManager = dataInput;

            } catch (UnrecognizedPropertyException e) {
                System.out.println("Unrecognized property. Please check the JSON file for errors.");
            } catch (InvalidTypeIdException e) {
                System.out.println("Invalid type id. Please check the JSON file for errors.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check the file name and try again.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public void setInventoryManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    

}

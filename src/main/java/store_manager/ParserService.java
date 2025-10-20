package store_manager;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;

public class ParserService {
    

    private StoreFront storeFront;

    public ParserService(StoreFront storeFront) {
        this.storeFront = storeFront;

    }
     /*
     * Process the message and return a response
     */
    public String processMessage(String message) {
        List<ProductWithQuantity> itemList = this.storeFront.getInventoryManager().fetchAllProducts();
        // split the message into parts using a space as the delimiter
        String[] parts = message.split("\\|");

        String command = parts[0]; // expected inputs: ADD, UPDATE, DELETE, GET, FIND, OLDER, YOUNGER, ALL, QUIT 
        List<ProductWithQuantity> output = new ArrayList<>(); // always return a list of Person objects whether empty or not
        String jsonResponse = ""; // initial string is empty but will be converted to JSON later

        // possible to throw exceptions if the input is not valid. Catch and return an empty list
        try {
            switch (command) {
                case "GET":
                    int id = Integer.parseInt(parts[1]);
                    ProductWithQuantity item = storeFront.getInventoryManager().getProductWithQuantity(id);
                    if (item != null) {
                        output.add(item);
                    }
                    break;
                case "FIND":
                    String name = parts[1];
                    List<ProductWithQuantity> itemsFound = storeFront.getInventoryManager().searchList(name);
                    output.addAll(itemsFound);
                    break;
                case "ADD": //formatted ADD|ID|Instrument|Name|Description|Price|Amount|Date|Special1|Special2
                    SalableProduct newSalableProduct = new SalableProduct();
                    String newType = parts[2];
                    switch (newType)
                    {
                    case "GUITAR":
                    newSalableProduct = new Guitar();
                    break;
                    case "DRUMS":
                    newSalableProduct = new Drums();
                    break;
                    case "KEYS":
                    newSalableProduct = new Keys();
                    break;
                    }

                    int case3ID = itemList.size() + 1;
                    newSalableProduct.setId(case3ID);

                    newSalableProduct.setName(parts[3]);

                    newSalableProduct.setDescription(parts[4]);

                    newSalableProduct.setPrice(Double.parseDouble(parts[5]));
                    
                    int newAmount = Integer.parseInt(parts[6]);

                    newSalableProduct.setDateOfManufacture((parts[7]));

                    if (newSalableProduct instanceof Guitar) {
                        ((Guitar)newSalableProduct).setType(parts[8]);

                        int numStrings = Integer.parseInt(parts[9]);
                        String guitarString;
                        List<String> guitarStrings = new ArrayList<>();
                        int counter = 10;
                        for (int i = 0; i < numStrings; i++) {
                            guitarStrings.add(parts[counter]);
                            counter++;
                        }
                        ((Guitar)newSalableProduct).setStrings(guitarStrings);

                    } else if (newSalableProduct instanceof Drums) {
                        String drumType = parts[8];
                        ((Drums)newSalableProduct).setDrumType(drumType);
                        double drumDiameter = Double.parseDouble(parts[9]);
                        ((Drums)newSalableProduct).setDiameter(drumDiameter);
                        
                    } else if (newSalableProduct instanceof Keys) {
                        int numKeys = Integer.parseInt(parts[8]);
                        ((Keys)newSalableProduct).setNumberOfKeys(numKeys);
                        ((Keys)newSalableProduct).setType(parts[9]);
                    }

                    ProductWithQuantity newProductWithQuantity = new ProductWithQuantity(newSalableProduct, newAmount);
                    this.storeFront.getInventoryManager().addProductWithQuantity(newProductWithQuantity);
                    output.add(newProductWithQuantity);
                    break;
                
                case "UPDATE": //formatted UPDATE|ID|Name|Description|Price|Amount|Date|Special1|Special2
                    ProductWithQuantity itemToUpdate = null;
                    int num = -1;
                    int updateId = Integer.parseInt(parts[1]);
                    for (int i = 0; i < itemList.size(); i++) {//itemList is accessible from this class for comparison purposes
                        if (updateId == (itemList.get(i).getSalableProduct().getId())) {//checks whole itemList to see if an item with the inputted name exists, then equates ith with itemToUpdate
                            itemToUpdate = itemList.get(i);
                            num = i;
                            break;
                        }
                    }

                    itemToUpdate.getSalableProduct().setName(parts[2]);

                    itemToUpdate.getSalableProduct().setDescription(parts[3]);

                    itemToUpdate.getSalableProduct().setPrice(Double.parseDouble(parts[4]));
                    
                    itemToUpdate.setQuantity(Integer.parseInt(parts[5]));

                    itemToUpdate.getSalableProduct().setDateOfManufacture(parts[6]);

                    if (itemToUpdate.getSalableProduct() instanceof Guitar) {
                        ((Guitar)itemToUpdate.getSalableProduct()).setType(parts[7]);

                        int numStrings = Integer.parseInt(parts[8]);
                        List<String> guitarStrings = new ArrayList<>();
                        int counter = 10;
                        for (int i = 0; i < numStrings; i++) {
                            guitarStrings.add(parts[counter]);
                            counter++;
                        }
                        ((Guitar)itemToUpdate.getSalableProduct()).setStrings(guitarStrings);

                    } else if (itemToUpdate.getSalableProduct() instanceof Drums) {
                        String drumType = parts[7];
                        ((Drums)itemToUpdate.getSalableProduct()).setDrumType(drumType);
                        double drumDiameter = Double.parseDouble(parts[8]);
                        ((Drums)itemToUpdate.getSalableProduct()).setDiameter(drumDiameter);
                        
                    } else if (itemToUpdate.getSalableProduct() instanceof Keys) {
                        int numKeys = Integer.parseInt(parts[7]);
                        ((Keys)itemToUpdate.getSalableProduct()).setNumberOfKeys(numKeys);
                        ((Keys)itemToUpdate.getSalableProduct()).setType(parts[8]);
                    }
                    output.add(itemToUpdate);
                    break;
                
                case "DELETE": //format DELETE|ID|Amount
                    int deleteId = Integer.parseInt(parts[1]);
                    int deleteAmount = Integer.parseInt(parts[2]);
                    this.storeFront.getInventoryManager().removeProductWithQuantity(deleteAmount, deleteId);
                    break;
                case "ALL":
                    output.addAll(storeFront.getInventoryManager().fetchAllProducts());
                    break;
                case "QUIT":
                default:
                    output = new ArrayList<>();
            }

            // serialize the output list to a JSON string using Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper(); 
            jsonResponse = objectMapper.writeValueAsString(output);

        } catch (Exception e) {
            System.out.println("Error processing message: " + message);
            jsonResponse = "[]";
            return jsonResponse;
        }
        return jsonResponse;
    }
}

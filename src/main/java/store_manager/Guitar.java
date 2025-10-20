package store_manager;

import java.util.List;
/*
 * guitar subclass of SalableProduct with unique guitar attributes
 */
public class Guitar extends SalableProduct {
    private String type;
    private List<String> strings;
    
    public Guitar(int id, String name, String description, double price, String type, List<String> strings, String dateOfManufacture) {
        super(id, name, description, price, dateOfManufacture);
        this.type = type;
        this.strings = strings;
    }
    public Guitar() {
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<String> getStrings() {
        return strings;
    }
    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public String toString() {
        return "Guitars [type= " + type + ", strings= " + strings + ", ID= " + getId() + ", name= " + getName()
                + ", description= " + getDescription()
                + ", price= " + getPrice() + ", dateOfManufacture= " + getDateOfManufacture() + "]";
    }

    
}

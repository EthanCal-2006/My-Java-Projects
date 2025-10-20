package store_manager;

/*
 * keys subclass of SalableProduct with unique keys attributes
 */
public class Keys extends SalableProduct{
    private int numberOfKeys;
    private String type;
    
    public Keys(int id, String name, String description, double price, int numberOfKeys, String type, String dateOfManufacture) {
        super(id, name, description, price, dateOfManufacture);
        this.numberOfKeys = numberOfKeys;
        this.type = type;
    }

    public Keys() {
    }

    public int getNumberOfKeys() {
        return numberOfKeys;
    }

    public void setNumberOfKeys(int numberOfKeys) {
        this.numberOfKeys = numberOfKeys;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Keyboards [numberOfKeys= " + numberOfKeys + ", type= " + type + ", ID= " + getId() + ", name= "
                + getName() + ", Description= " + getDescription() + ", Price= " + getPrice()  + ", dateOfManufacture= " + getDateOfManufacture() + "]";
    }

    

    
}

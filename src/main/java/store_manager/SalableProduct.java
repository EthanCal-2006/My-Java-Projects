package store_manager;

import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonSubTypes({
    @Type(value = Guitar.class, name = "guitar"),
    @Type(value = Drums.class, name = "drums"),
    @Type(value = Keys.class, name = "keys")
})
/*
 * outline for common attributes of items in the store
 */
public class SalableProduct {

    protected int id;
    protected String name;
    protected String description;
    protected double price;
    protected String dateOfManufacture;
    /*
     * overloaded constructor
     */
    public SalableProduct(int id, String name, String description, double price, String dateOfManufacture) {
        super();//inherits stuff from default constructor
        this.id = id;
        this.name = name;
        this.description = description;
        DecimalFormat df = new DecimalFormat("#.##"); //rounds to 2 decimal points
        String formattedNumber = df.format(price);
        this.price = Double.parseDouble(formattedNumber);
        this.dateOfManufacture = dateOfManufacture;
    }
    /*
     * default constructor
     */
    public SalableProduct() {
        this.id = -1;
        this.name = "NONAME";
        this.description = "NODESC";
        this.price = -1.0;
        this.dateOfManufacture = "00/00/0000";
    }



    @Override
    public String toString() {
        return "ID: " + this.id + ", Name: " + this.name + ", Description: " + this.description + ", Price: " + this.price + ", dateOfManufacture: " + this.dateOfManufacture;
    }
    /*
     * compares item IDs for the purpose of organizing
     */
    public int compareTo(SalableProduct other) {        
        if (this.id > other.id) {
            return 1;
        }
        else if (this.id < other.id) {
            return -1;
        }
        else {
            return 0;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getDateOfManufacture() {
        return dateOfManufacture;
    }
    public void setDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    
}

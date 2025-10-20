package store_manager;

/*
 * drums subclass of SalableProduct with unique drums attributes
 */
public class Drums extends SalableProduct {
    private String drumType;
    private double diameter;
    
    public Drums(int id, String name, String description, double price, String drumType,
            double diameter, String dateOfManufacture) {
        super(id, name, description, price, dateOfManufacture);
        this.drumType = drumType;
        this.diameter = diameter;
        this.dateOfManufacture = dateOfManufacture;
    }

    public Drums() {
    }

    public String getDrumType() {
        return drumType;
    }

    public void setDrumType(String drumType) {
        this.drumType = drumType;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    @Override
    public String toString() {
        return "Drums [drumType= " + drumType + ", diameter= " + diameter + ", ID= " + getId() + ", name= "
                + getName() + ", description= " + getDescription() + ", price= " + getPrice()  + ", dateOfManufacture= " + getDateOfManufacture() +  "]";
    }

    
}

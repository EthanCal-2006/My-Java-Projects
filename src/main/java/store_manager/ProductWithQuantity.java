package store_manager;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
/*
 * assigns a product with a number that the inventory has of it
 */
public class ProductWithQuantity {
    
    private SalableProduct salableProduct;
    private int quantity;
    /*
     * constructor
     */
    public ProductWithQuantity(SalableProduct salableProduct, int quantity) {
        this.salableProduct = salableProduct;
        this.quantity = quantity;
    }

    public ProductWithQuantity(ProductWithQuantity productWithQuantity) {
        this.salableProduct = productWithQuantity.getSalableProduct();
        this.quantity = productWithQuantity.getQuantity();
    }
    
    public ProductWithQuantity() {
    }

    public SalableProduct getSalableProduct() {
        return salableProduct;
    }
    public void setSalableProduct(SalableProduct salableProduct) {
        this.salableProduct = salableProduct;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "[Item: " + getSalableProduct() + ", quantity= " + getQuantity() + "]";
    }

    
}

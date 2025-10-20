package store_manager;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<ProductWithQuantity> cartList;

    public ShoppingCart() {
        this.cartList = new ArrayList<>();
    }

    public List<ProductWithQuantity> getAllCartItems() {
        return this.cartList;
    }

    public void addProductWithQuantity(ProductWithQuantity productWithQuantity) {
        this.cartList.add(productWithQuantity);
    }

    public void removeProductWithQuantity(int numItemsRemoved, int idOfItemRemoved) {
        int numItemsLeftCart;
        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getSalableProduct().getId()==(idOfItemRemoved)) {
                numItemsLeftCart = cartList.get(i).getQuantity() - numItemsRemoved;
                    if (numItemsLeftCart <= 0) {
                        numItemsLeftCart = 0;
                        cartList.remove(i);
                        i--;
                    } else {
                        cartList.get(i).setQuantity(numItemsLeftCart);
                    }
            }
        }
    }

}

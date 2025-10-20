package store_manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ShoppingCartTest {

    private ShoppingCart shoppingCart;
    private SalableProductStub product1;
    private SalableProductStub product2;
    private ProductWithQuantity pwq1;
    private ProductWithQuantity pwq2;

    @BeforeEach
    void setUp() {
        shoppingCart = new ShoppingCart();
        product1 = new SalableProductStub(1, "Cart Product 1");
        product2 = new SalableProductStub(2, "Cart Product 2");
        pwq1 = new ProductWithQuantity(product1, 3);
        pwq2 = new ProductWithQuantity(product2, 7);
        shoppingCart.addProductWithQuantity(pwq1);
        shoppingCart.addProductWithQuantity(pwq2);
    }

    @Test
    void getAllCartItems_shouldReturnAllItemsInCart() {
        List<ProductWithQuantity> cartItems = shoppingCart.getAllCartItems();
        assertEquals(2, cartItems.size());
        assertTrue(cartItems.contains(pwq1));
        assertTrue(cartItems.contains(pwq2));
    }

    @Test
    void addProductWithQuantity_shouldAddProductToCart() {
        SalableProductStub newProduct = new SalableProductStub(3, "New Cart Product");
        ProductWithQuantity newPwq = new ProductWithQuantity(newProduct, 2);
        shoppingCart.addProductWithQuantity(newPwq);
        assertEquals(3, shoppingCart.getAllCartItems().size());
        assertTrue(shoppingCart.getAllCartItems().contains(newPwq));
    }

    @Test
    void removeProductWithQuantity_shouldDecreaseQuantityAndNotRemoveIfQuantityRemains() {
        shoppingCart.removeProductWithQuantity(1, 1);
        assertEquals(2, shoppingCart.getAllCartItems().size());
        assertEquals(2, shoppingCart.getAllCartItems().get(0).getQuantity());
    }

    @Test
    void removeProductWithQuantity_shouldRemoveItemIfQuantityBecomesZero() {
        shoppingCart.removeProductWithQuantity(3, 1);
        assertEquals(1, shoppingCart.getAllCartItems().size());
        assertFalse(shoppingCart.getAllCartItems().contains(pwq1));
    }

    @Test
    void removeProductWithQuantity_shouldRemoveItemIfRemovingMoreThanAvailable() {
        shoppingCart.removeProductWithQuantity(5, 1);
        assertEquals(1, shoppingCart.getAllCartItems().size());
        assertFalse(shoppingCart.getAllCartItems().contains(pwq1));
    }

    @Test
    void removeProductWithQuantity_shouldNotModifyCartIfIdNotFound() {
        shoppingCart.removeProductWithQuantity(2, 4);
        assertEquals(2, shoppingCart.getAllCartItems().size());
        assertTrue(shoppingCart.getAllCartItems().contains(pwq1));
        assertTrue(shoppingCart.getAllCartItems().contains(pwq2));
    }
}
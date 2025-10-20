package store_manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SalableProductStub extends SalableProduct {
    private int id;
    private String name;

    public SalableProductStub(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalableProductStub that = (SalableProductStub) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SalableProductStub{id=" + id + ", name='" + name + '\'' + '}';
    }
}

class ProductWithQuantityTest {

    @Test
    void constructor_shouldSetSalableProductAndQuantity() {
        SalableProductStub product = new SalableProductStub(1, "Test Product");
        ProductWithQuantity pwq = new ProductWithQuantity(product, 10);
        assertEquals(product, pwq.getSalableProduct());
        assertEquals(10, pwq.getQuantity());
    }

    @Test
    void copyConstructor_shouldCreateDeepCopy() {
        SalableProductStub originalProduct = new SalableProductStub(2, "Original Product");
        ProductWithQuantity originalPwq = new ProductWithQuantity(originalProduct, 5);
        ProductWithQuantity copyPwq = new ProductWithQuantity(originalPwq);

        assertEquals(originalPwq.getSalableProduct(), copyPwq.getSalableProduct());
        assertEquals(originalPwq.getQuantity(), copyPwq.getQuantity());
        assertNotSame(originalPwq.getSalableProduct(), copyPwq.getSalableProduct()); // Ensure SalableProduct is also copied (in this stubbed case, it will be the same instance but in a real scenario, it should be a new object)
    }

    @Test
    void defaultConstructor_shouldInitializeWithNullProductAndZeroQuantity() {
        ProductWithQuantity pwq = new ProductWithQuantity();
        assertNull(pwq.getSalableProduct());
        assertEquals(0, pwq.getQuantity());
    }

    @Test
    void getAndSetSalableProduct_shouldWorkCorrectly() {
        ProductWithQuantity pwq = new ProductWithQuantity();
        SalableProductStub product = new SalableProductStub(3, "Another Product");
        pwq.setSalableProduct(product);
        assertEquals(product, pwq.getSalableProduct());
    }

    @Test
    void getAndSetQuantity_shouldWorkCorrectly() {
        ProductWithQuantity pwq = new ProductWithQuantity();
        pwq.setQuantity(25);
        assertEquals(25, pwq.getQuantity());
    }

    @Test
    void toString_shouldReturnFormattedString() {
        SalableProductStub product = new SalableProductStub(4, "Yet Another Product");
        ProductWithQuantity pwq = new ProductWithQuantity(product, 7);
        String expectedString = "[Item: SalableProductStub{id=4, name='Yet Another Product'}, quantity= 7]";
        assertEquals(expectedString, pwq.toString());
    }
}

public class InventoryManagerTest {

    private InventoryManager inventoryManager;
    private SalableProductStub product1;
    private SalableProductStub product2;
    private ProductWithQuantity pwq1;
    private ProductWithQuantity pwq2;

    @BeforeEach
    void setUp() {
        inventoryManager = new InventoryManager(false);
        product1 = new SalableProductStub(1, "Test Product 1");
        product2 = new SalableProductStub(2, "Another Product");
        pwq1 = new ProductWithQuantity(product1, 5);
        pwq2 = new ProductWithQuantity(product2, 10);
        inventoryManager.getItemList().add(pwq1);
        inventoryManager.getItemList().add(pwq2);
    }

    @Test
    void constructor_shouldInitializeInventory() {
        InventoryManager initializedManager = new InventoryManager(true);
        assertFalse(initializedManager.fetchAllProducts().isEmpty());
    }

    @Test
    void constructor_shouldNotInitializeInventory() {
        InventoryManager notInitializedManager = new InventoryManager(false);
        assertTrue(notInitializedManager.fetchAllProducts().isEmpty());
    }

    @Test
    void initializeInventory_shouldAddBaseProducts() {
        InventoryManager emptyManager = new InventoryManager(false);
        emptyManager.initializeInventory();
        assertEquals(4, emptyManager.fetchAllProducts().size());
    }

    @Test
    void fetchAllProducts_shouldReturnAllProducts() {
        List<ProductWithQuantity> allProducts = inventoryManager.fetchAllProducts();
        assertEquals(2, allProducts.size());
        assertTrue(allProducts.contains(pwq1));
        assertTrue(allProducts.contains(pwq2));
    }

    @Test
    void getProductWithQuantity_shouldReturnProductIfExists() {
        ProductWithQuantity foundProduct = inventoryManager.getProductWithQuantity(1);
        assertEquals(pwq1, foundProduct);
    }

    @Test
    void getProductWithQuantity_shouldReturnNullIfNotExists() {
        ProductWithQuantity notFoundProduct = inventoryManager.getProductWithQuantity(3);
        assertNull(notFoundProduct);
    }

    @Test
    void searchList_shouldReturnMatchingProducts() {
        List<ProductWithQuantity> searchResults = inventoryManager.searchList("Test");
        assertEquals(1, searchResults.size());
        assertTrue(searchResults.contains(pwq1));
    }

    @Test
    void searchList_shouldReturnEmptyListIfNoMatch() {
        List<ProductWithQuantity> searchResults = inventoryManager.searchList("NonExistent");
        assertTrue(searchResults.isEmpty());
    }

    @Test
    void addProductWithQuantity_shouldAddProductToItemList() {
        SalableProductStub newProduct = new SalableProductStub(3, "New Product");
        ProductWithQuantity newPwq = new ProductWithQuantity(newProduct, 7);
        inventoryManager.addProductWithQuantity(newPwq);
        assertEquals(3, inventoryManager.fetchAllProducts().size());
        assertTrue(inventoryManager.fetchAllProducts().contains(newPwq));
    }

    @Test
    void addProductWithQuantity_shouldIncreaseQuantityIfIdExists() {
        inventoryManager.addProductWithQuantity(3, 1);
        assertEquals(8, inventoryManager.getProductWithQuantity(1).getQuantity());
    }

    @Test
    void addProductWithQuantity_shouldNotModifyListIfIdDoesNotExist() {
        inventoryManager.addProductWithQuantity(3, 4);
        assertEquals(2, inventoryManager.fetchAllProducts().size());
    }

    @Test
    void removeProductWithQuantity_byName_shouldDecreaseQuantity() {
        inventoryManager.removeProductWithQuantity(2, "Test Product 1");
        assertEquals(3, inventoryManager.getProductWithQuantity(1).getQuantity());
    }

    @Test
    void removeProductWithQuantity_byName_shouldSetQuantityToZeroIfRemovingTooMany() {
        inventoryManager.removeProductWithQuantity(10, "Test Product 1");
        assertEquals(0, inventoryManager.getProductWithQuantity(1).getQuantity());
    }

    @Test
    void removeProductWithQuantity_byName_shouldNotModifyIfNameNotFound() {
        inventoryManager.removeProductWithQuantity(2, "NonExistent Product");
        assertEquals(5, inventoryManager.getProductWithQuantity(1).getQuantity());
        assertEquals(10, inventoryManager.getProductWithQuantity(2).getQuantity());
    }

    @Test
    void removeProductWithQuantity_byId_shouldDecreaseQuantity() {
        inventoryManager.removeProductWithQuantity(4, 2);
        assertEquals(6, inventoryManager.getProductWithQuantity(2).getQuantity());
    }

    @Test
    void removeProductWithQuantity_byId_shouldSetQuantityToZeroIfRemovingTooMany() {
        inventoryManager.removeProductWithQuantity(15, 2);
        assertEquals(0, inventoryManager.getProductWithQuantity(2).getQuantity());
    }

    @Test
    void removeProductWithQuantity_byId_shouldNotModifyIfIdNotFound() {
        inventoryManager.removeProductWithQuantity(2, 3);
        assertEquals(5, inventoryManager.getProductWithQuantity(1).getQuantity());
        assertEquals(10, inventoryManager.getProductWithQuantity(2).getQuantity());
    }

    @Test
    void updateProductWithQuantity_shouldUpdateExistingProduct() {
        SalableProductStub updatedProduct = new SalableProductStub(1, "Test Product 1");
        ProductWithQuantity updatedPwq = new ProductWithQuantity(updatedProduct, 12);
        inventoryManager.updateProductWithQuantity(updatedPwq);
        ProductWithQuantity retrievedProduct = inventoryManager.getProductWithQuantity(1);
        assertEquals("Test Product 1", retrievedProduct.getSalableProduct().getName());
        assertEquals(5, retrievedProduct.getQuantity());
    }

    @Test
    void updateProductWithQuantity_shouldNotUpdateNonExistingProduct() {
        SalableProductStub nonExistingProduct = new SalableProductStub(3, "NonExisting");
        ProductWithQuantity nonExistingPwq = new ProductWithQuantity(nonExistingProduct, 5);
        inventoryManager.updateProductWithQuantity(nonExistingPwq);
        assertEquals(2, inventoryManager.fetchAllProducts().size());
        assertNull(inventoryManager.getProductWithQuantity(3));
    }

    @Test
    void setItemList_shouldReplaceExistingList() {
        List<ProductWithQuantity> newList = new ArrayList<>();
        SalableProductStub newProd = new SalableProductStub(4, "New Prod");
        newList.add(new ProductWithQuantity(newProd, 20));
        inventoryManager.setItemList(newList);
        assertEquals(1, inventoryManager.fetchAllProducts().size());
        assertEquals(20, inventoryManager.fetchAllProducts().get(0).getQuantity());
        assertEquals(4, inventoryManager.fetchAllProducts().get(0).getSalableProduct().getId());
    }

    @Test
    void getItemList_shouldReturnTheInternalItemList() {
        List<ProductWithQuantity> internalList = inventoryManager.getItemList();
        assertEquals(2, internalList.size());
        assertSame(internalList, inventoryManager.fetchAllProducts());
    }
}


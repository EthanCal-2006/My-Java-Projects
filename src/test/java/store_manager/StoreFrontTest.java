package store_manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class StoreFrontTest {

    @TempDir
    Path tempDir;

    private InventoryManager createSampleInventoryManager() {
        InventoryManager inventoryManager = new InventoryManager(false);
        SalableProduct product1 = new Drums(1, "War drum", "deep as the ocean", 600, "bass", 12.0, "01/25/2006");
        SalableProduct product2 = new Keys(3, "Manhassett", "Beethoven would be proud", 500, 77, "Grand Piano", "05/25/2000");
        inventoryManager.addProductWithQuantity(new ProductWithQuantity(product1, 1));
        inventoryManager.addProductWithQuantity(new ProductWithQuantity(product2, 2));
        return inventoryManager;
    }

    @Test
    void testSaveInventoryToFile_SuccessfulSave() throws IOException {
        StoreFront storeFront = new StoreFront();
        InventoryManager inventoryManager = createSampleInventoryManager();
        storeFront.setInventoryManager(inventoryManager);
        Path filePath = tempDir.resolve("test_save_success.json");
        String fileName = filePath.toString();

        System.out.println("Inventory before save: " + inventoryManager.fetchAllProducts()); // Debug
        boolean saveResult = storeFront.saveInventoryToFile(fileName);
        System.out.println("saveResult: " + saveResult); // Debug
        assertTrue(saveResult);
        assertTrue(Files.exists(filePath));
        assertTrue(Files.readString(filePath).contains("\"name\" : \"War drum\""));
    }

    @Test
    void testSaveInventoryToFile_IOException() throws IOException {
        StoreFront storeFront = new StoreFront();
        storeFront.setInventoryManager(createSampleInventoryManager());
        String invalidFileName = "/invalid/directory/test_save_ioexception.json";

        boolean saveResult = storeFront.saveInventoryToFile(invalidFileName);
        assertFalse(saveResult);

        java.io.ByteArrayOutputStream err = new java.io.ByteArrayOutputStream();
        System.setErr(new java.io.PrintStream(err));
        storeFront.saveInventoryToFile(invalidFileName);
        System.setErr(System.err);
        assertTrue(err.toString().contains("java.io.IOException"));
    }

    @Test
    void testLoadInventoryFromFile_SuccessfulLoad() throws IOException {
        StoreFront storeFrontToSave = new StoreFront();
        InventoryManager inventoryToSave = createSampleInventoryManager();
        storeFrontToSave.setInventoryManager(inventoryToSave);
        Path filePath = tempDir.resolve("test_load_success.json");
        storeFrontToSave.saveInventoryToFile(filePath.toString());

        StoreFront storeFrontToLoad = new StoreFront();
        storeFrontToLoad.loadInventoryFromFile(filePath.toString());
        InventoryManager loadedInventory = storeFrontToLoad.getInventoryManager();

        assertNotNull(loadedInventory);
        assertEquals(inventoryToSave.fetchAllProducts().size(), loadedInventory.fetchAllProducts().size());
        assertEquals(inventoryToSave.fetchAllProducts().get(0).getSalableProduct().getName(),
                     loadedInventory.fetchAllProducts().get(0).getSalableProduct().getName());
    }

    @Test
    void testLoadInventoryFromFile_FileNotFound() {
        StoreFront storeFront = new StoreFront();
        String nonExistentFile = "non_existent.json";

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        storeFront.loadInventoryFromFile(nonExistentFile);

        System.setOut(System.out);
        assertTrue(out.toString().contains("File not found. Please check the file name and try again."));
        assertNotNull(storeFront.getInventoryManager());
        assertTrue(storeFront.getInventoryManager().fetchAllProducts().isEmpty());
    }

    @Test
    void testLoadInventoryFromFile_IOExceptionDuringRead() throws IOException {
        // Create a temporary file
        Path filePath = tempDir.resolve("test_read_ioexception.json");
        Files.writeString(filePath, "Some content");
        String fileName = filePath.toString();

        // Create a custom BufferedReader that throws an IOException
        BufferedReader throwingBufferedReader = new BufferedReader(new FileReader(fileName)) {
            private boolean firstRead = true;
            @Override
            public String readLine() throws IOException {
                if (firstRead) {
                    firstRead = false;
                    throw new IOException("Simulated read exception");
                }
                return super.readLine();
            }

            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                throw new IOException("Simulated read exception");
            }

            @Override
            public int read() throws IOException {
                throw new IOException("Simulated read exception");
            }
        };

        // Use reflection to inject the custom BufferedReader
        StoreFront storeFront = new StoreFront();
        try {
            java.lang.reflect.Field readerField = StoreFront.class.getDeclaredField("reader");
            readerField.setAccessible(true);
            readerField.set(storeFront, throwingBufferedReader);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to inject throwing BufferedReader: " + e.getMessage());
        }

        java.io.ByteArrayOutputStream err = new java.io.ByteArrayOutputStream();
        System.setErr(new PrintStream(err));

        storeFront.loadInventoryFromFile(fileName);

        System.setErr(System.err);
        assertTrue(err.toString().contains("java.io.IOException: Simulated read exception"));
        assertNotNull(storeFront.getInventoryManager());
        assertTrue(storeFront.getInventoryManager().fetchAllProducts().isEmpty());

        // Clean up the injected field (optional)
        try {
            java.lang.reflect.Field readerField = StoreFront.class.getDeclaredField("reader");
            readerField.setAccessible(true);
            readerField.set(storeFront, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Handle cleanup error
        }
    }

    @Test
    void testLoadInventoryFromFile_UnrecognizedPropertyException() throws IOException {
        Path filePath = tempDir.resolve("test_unrecognized_property.json");
        Files.writeString(filePath, "{ \"itemList\" : [ { \"@class\" : \"com.ethancalhoun.ProductWithQuantity\", \"salableProduct\" : { \"@class\" : \"com.ethancalhoun.Drums\", \"unknownProperty\" : \"value\" } } ] }");
        String fileName = filePath.toString();

        StoreFront storeFront = new StoreFront();
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        storeFront.loadInventoryFromFile(fileName);

        System.setOut(System.out);
        assertTrue(out.toString().contains("Unrecognized property. Please check the JSON file for errors."));
        assertNotNull(storeFront.getInventoryManager());
        assertTrue(storeFront.getInventoryManager().fetchAllProducts().isEmpty());
    }

    @Test
    void testLoadInventoryFromFile_InvalidTypeIdException() throws IOException {
        Path filePath = tempDir.resolve("test_invalid_type_id.json");
        Files.writeString(filePath, "{ \"itemList\" : [ { \"@class\" : \"com.ethancalhoun.NonExistentClass\" } ] }");
        String fileName = filePath.toString();

        StoreFront storeFront = new StoreFront();
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        storeFront.loadInventoryFromFile(fileName);

        System.setOut(System.out);
        assertTrue(out.toString().contains("Invalid type id. Please check the JSON file for errors."));
        assertNotNull(storeFront.getInventoryManager());
        assertTrue(storeFront.getInventoryManager().fetchAllProducts().isEmpty());
    }

    @Test
    void testLoadInventoryFromFile_EmptyFile() throws IOException {
        Path filePath = tempDir.resolve("test_empty_file.json");
        Files.writeString(filePath, "");
        String fileName = filePath.toString();

        StoreFront storeFront = new StoreFront();
        storeFront.loadInventoryFromFile(fileName);

        assertNotNull(storeFront.getInventoryManager());
        assertTrue(storeFront.getInventoryManager().fetchAllProducts().isEmpty());
    }

    @Test
    void testLoadInventoryFromFile_FileWithOnlyWhitespace() throws IOException {
        Path filePath = tempDir.resolve("test_whitespace_file.json");
        Files.writeString(filePath, "   \n\t  ");
        String fileName = filePath.toString();

        StoreFront storeFront = new StoreFront();
        storeFront.loadInventoryFromFile(fileName);

        assertNotNull(storeFront.getInventoryManager());
        assertTrue(storeFront.getInventoryManager().fetchAllProducts().isEmpty());
    }
}
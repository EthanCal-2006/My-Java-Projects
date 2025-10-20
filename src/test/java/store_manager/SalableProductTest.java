package store_manager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class SalableProductTest {

    // Tests for Keys class
    @Test
    public void testKeysConstructorWithAllArguments() {
        LocalDate manufactureDate = LocalDate.now();
        Keys keys = new Keys(101, "Mechanical Keyboard", "Ergonomic mechanical keyboard", 99.99, 104, "Cherry MX Brown", manufactureDate.format(DateTimeFormatter.ISO_DATE));
        assertEquals(101, keys.getId());
        assertEquals("Mechanical Keyboard", keys.getName());
        assertEquals("Ergonomic mechanical keyboard", keys.getDescription());
        assertEquals(99.99, keys.getPrice(), 0.001);
        assertEquals(104, keys.getNumberOfKeys());
        assertEquals("Cherry MX Brown", keys.getType());
        assertEquals(manufactureDate.format(DateTimeFormatter.ISO_DATE), keys.getDateOfManufacture());
    }

    @Test
    public void testKeysDefaultConstructor() {
        Keys keys = new Keys();
        assertNotNull(keys);
        assertEquals(-1, keys.getId());
        assertEquals("NONAME", keys.getName());
        assertEquals("NODESC", keys.getDescription());
        assertEquals(-1.0, keys.getPrice(), 0.001);
        assertEquals("00/00/0000", keys.getDateOfManufacture());
    }

    @Test
    public void testKeysGettersAndSetters() {
        Keys keys = new Keys();
        keys.setNumberOfKeys(87);
        assertEquals(87, keys.getNumberOfKeys());
        keys.setType("Tenkeyless");
        assertEquals("Tenkeyless", keys.getType());
        keys.setId(102);
        assertEquals(102, keys.getId());
        keys.setName("Gaming Keyboard");
        assertEquals("Gaming Keyboard", keys.getName());
        keys.setDescription("RGB backlit gaming keyboard");
        assertEquals("RGB backlit gaming keyboard", keys.getDescription());
        keys.setPrice(129.99);
        assertEquals(129.99, keys.getPrice(), 0.001);
        LocalDate manufactureDate = LocalDate.of(2024, 12, 25);
        keys.setDateOfManufacture(manufactureDate.format(DateTimeFormatter.ISO_DATE));
        assertEquals(manufactureDate.format(DateTimeFormatter.ISO_DATE), keys.getDateOfManufacture());
    }

    @Test
    public void testKeysToString() {
        LocalDate manufactureDate = LocalDate.of(2025, 1, 15);
        Keys keys = new Keys(103, "Wireless Keyboard", "Bluetooth wireless keyboard", 59.99, 75, "Low Profile", manufactureDate.format(DateTimeFormatter.ISO_DATE));
        String expectedString = "Keyboards [numberOfKeys= 75, type= Low Profile, ID= 103, name= Wireless Keyboard, Description= Bluetooth wireless keyboard, Price= 59.99, dateOfManufacture= 2025-01-15]";
        assertEquals(expectedString, keys.toString());
    }

    @Test
    public void testKeysSpecialCases() {
        Keys keys = new Keys();
        keys.setNumberOfKeys(0);
        assertEquals(0, keys.getNumberOfKeys());
        keys.setPrice(0.0);
        assertEquals(0.0, keys.getPrice(), 0.001);
        keys.setName(null);
        assertNull(keys.getName());
        keys.setDescription("");
        assertEquals("", keys.getDescription());
        keys.setDateOfManufacture("2025/04/23"); // Invalid ISO format
        assertEquals("2025/04/23", keys.getDateOfManufacture());
        keys.setType(null);
        assertNull(keys.getType());
        keys.setType("");
        assertEquals("", keys.getType());
    }

    // Tests for Guitar class
    @Test
    public void testGuitarConstructorWithAllArguments() {
        LocalDate manufactureDate = LocalDate.now();
        List<String> strings = Arrays.asList("E", "A", "D", "G", "B", "e");
        Guitar guitar = new Guitar(201, "Stratocaster", "Electric guitar", 799.99, "Electric", strings, manufactureDate.format(DateTimeFormatter.ISO_DATE));
        assertEquals(201, guitar.getId());
        assertEquals("Stratocaster", guitar.getName());
        assertEquals("Electric guitar", guitar.getDescription());
        assertEquals(799.99, guitar.getPrice(), 0.001);
        assertEquals("Electric", guitar.getType());
        assertEquals(strings, guitar.getStrings());
        assertEquals(manufactureDate.format(DateTimeFormatter.ISO_DATE), guitar.getDateOfManufacture());
    }

    @Test
    public void testGuitarDefaultConstructor() {
        Guitar guitar = new Guitar();
        assertNotNull(guitar);
        assertEquals(-1, guitar.getId());
        assertEquals("NONAME", guitar.getName());
        assertEquals("NODESC", guitar.getDescription());
        assertEquals(-1.0, guitar.getPrice(), 0.001);
        assertEquals("00/00/0000", guitar.getDateOfManufacture());
    }

    @Test
    public void testGuitarGettersAndSetters() {
        Guitar guitar = new Guitar();
        List<String> strings1 = Arrays.asList("E", "A", "D", "G", "B", "e");
        List<String> strings2 = Arrays.asList("B", "E", "A", "D", "F#", "B");
        LocalDate manufactureDate = LocalDate.of(2024, 11, 30);
        guitar.setType("Acoustic");
        assertEquals("Acoustic", guitar.getType());
        guitar.setStrings(strings1);
        assertEquals(strings1, guitar.getStrings());
        guitar.setId(202);
        assertEquals(202, guitar.getId());
        guitar.setName("Les Paul");
        assertEquals("Les Paul", guitar.getName());
        guitar.setDescription("Solid body electric guitar");
        assertEquals("Solid body electric guitar", guitar.getDescription());
        guitar.setPrice(1599.00);
        assertEquals(1599.00, guitar.getPrice(), 0.001);
        guitar.setDateOfManufacture(manufactureDate.format(DateTimeFormatter.ISO_DATE));
        assertEquals(manufactureDate.format(DateTimeFormatter.ISO_DATE), guitar.getDateOfManufacture());
        guitar.setStrings(strings2);
        assertEquals(strings2, guitar.getStrings());
    }

    @Test
    public void testGuitarToString() {
        LocalDate manufactureDate = LocalDate.of(2025, 3, 10);
        List<String> strings = Arrays.asList("E", "A", "D", "G", "B", "e");
        Guitar guitar = new Guitar(203, "Telecaster", "Versatile electric guitar", 899.50, "Electric", strings, manufactureDate.format(DateTimeFormatter.ISO_DATE));
        String expectedString = "Guitars [type= Electric, strings= [E, A, D, G, B, e], ID= 203, name= Telecaster, description= Versatile electric guitar, price= 899.5, dateOfManufacture= 2025-03-10]";
        assertEquals(expectedString, guitar.toString());
    }

    @Test
    public void testGuitarSpecialCases() {
        Guitar guitar = new Guitar();
        guitar.setType(null);
        assertNull(guitar.getType());
        guitar.setType("");
        assertEquals("", guitar.getType());
        guitar.setStrings(null);
        assertNull(guitar.getStrings());
        guitar.setStrings(Arrays.asList());
        assertEquals(Arrays.asList(), guitar.getStrings());
        guitar.setName(null);
        assertNull(guitar.getName());
        guitar.setDescription("");
        assertEquals("", guitar.getDescription());
        guitar.setPrice(0.0);
        assertEquals(0.0, guitar.getPrice(), 0.001);
        guitar.setDateOfManufacture("2025-04-23T12:00:00Z"); // Invalid ISO_DATE format
        assertEquals("2025-04-23T12:00:00Z", guitar.getDateOfManufacture());
    }

    // Tests for Drums class
    @Test
    public void testDrumsConstructorWithAllArguments() {
        LocalDate manufactureDate = LocalDate.now();
        Drums drums = new Drums(301, "Snare Drum", "Standard snare drum", 199.00, "Snare", 14.0, manufactureDate.format(DateTimeFormatter.ISO_DATE));
        assertEquals(301, drums.getId());
        assertEquals("Snare Drum", drums.getName());
        assertEquals("Standard snare drum", drums.getDescription());
        assertEquals(199.00, drums.getPrice(), 0.001);
        assertEquals("Snare", drums.getDrumType());
        assertEquals(14.0, drums.getDiameter(), 0.001);
        assertEquals(manufactureDate.format(DateTimeFormatter.ISO_DATE), drums.getDateOfManufacture());
    }

    @Test
    public void testDrumsDefaultConstructor() {
        Drums drums = new Drums();
        assertNotNull(drums);
        assertEquals(-1, drums.getId());
        assertEquals("NONAME", drums.getName());
        assertEquals("NODESC", drums.getDescription());
        assertEquals(-1.0, drums.getPrice(), 0.001);
        assertEquals("00/00/0000", drums.getDateOfManufacture());
    }

    @Test
    public void testDrumsGettersAndSetters() {
        Drums drums = new Drums();
        LocalDate manufactureDate = LocalDate.of(2024, 10, 15);
        drums.setDrumType("Tom");
        assertEquals("Tom", drums.getDrumType());
        drums.setDiameter(12.0);
        assertEquals(12.0, drums.getDiameter(), 0.001);
        drums.setId(302);
        assertEquals(302, drums.getId());
        drums.setName("Rack Tom");
        assertEquals("Rack Tom", drums.getName());
        drums.setDescription("Mounted tom drum");
        assertEquals("Mounted tom drum", drums.getDescription());
        drums.setPrice(149.50);
        assertEquals(149.50, drums.getPrice(), 0.001);
        drums.setDateOfManufacture(manufactureDate.format(DateTimeFormatter.ISO_DATE));
        assertEquals(manufactureDate.format(DateTimeFormatter.ISO_DATE), drums.getDateOfManufacture());
    }

    @Test
    public void testDrumsToString() {
        LocalDate manufactureDate = LocalDate.of(2025, 2, 20);
        Drums drums = new Drums(303, "Bass Drum", "Standard bass drum", 299.99, "Bass", 22.0, manufactureDate.format(DateTimeFormatter.ISO_DATE));
        String expectedString = "Drums [drumType= Bass, diameter= 22.0, ID= 303, name= Bass Drum, description= Standard bass drum, price= 299.99, dateOfManufacture= 2025-02-20]";
        assertEquals(expectedString, drums.toString());
    }

    @Test
    public void testDrumsSpecialCases() {
        Drums drums = new Drums();
        drums.setDrumType(null);
        assertNull(drums.getDrumType());
        drums.setDrumType("");
        assertEquals("", drums.getDrumType());
        drums.setDiameter(0.0);
        assertEquals(0.0, drums.getDiameter(), 0.001);
        drums.setDiameter(-5.0);
        assertEquals(-5.0, drums.getDiameter(), 0.001);
        drums.setName(null);
        assertNull(drums.getName());
        drums.setDescription("");
        assertEquals("", drums.getDescription());
        drums.setPrice(0.0);
        assertEquals(0.0, drums.getPrice(), 0.001);
        drums.setDateOfManufacture("23-04-2025"); // Another invalid format
        assertEquals("23-04-2025", drums.getDateOfManufacture());
    }

    // Tests for SalableProduct class
    @Test
    public void testSalableProductConstructorWithAllArguments() {
        LocalDate manufactureDate = LocalDate.now();
        SalableProduct product = new SalableProduct(401, "Generic Product", "A basic salable item", 49.95, manufactureDate.format(DateTimeFormatter.ISO_DATE));
        assertEquals(401, product.getId());
        assertEquals("Generic Product", product.getName());
        assertEquals("A basic salable item", product.getDescription());
        assertEquals(49.95, product.getPrice(), 0.001);
        assertEquals(manufactureDate.format(DateTimeFormatter.ISO_DATE), product.getDateOfManufacture());
    }

    @Test
    public void testSalableProductConstructorWithRounding() {
        SalableProduct product = new SalableProduct(402, "Rounded Product", "Price should be rounded", 123.456, "2025-04-23");
        assertEquals(123.46, product.getPrice(), 0.001);
    }

    @Test
    public void testSalableProductDefaultConstructor() {
        SalableProduct product = new SalableProduct();
        assertEquals(-1, product.getId());
        assertEquals("NONAME", product.getName());
        assertEquals("NODESC", product.getDescription());
        assertEquals(-1.0, product.getPrice(), 0.001);
        assertEquals("00/00/0000", product.getDateOfManufacture());
    }

    @Test
    public void testSalableProductToString() {
        SalableProduct product = new SalableProduct(403, "Test Item", "For testing toString", 99.99, "2025-04-24");
        String expectedString = "ID: 403, Name: Test Item, Description: For testing toString, Price: 99.99, dateOfManufacture: 2025-04-24";
        assertEquals(expectedString, product.toString());
    }

    @Test
    public void testSalableProductCompareTo() {
        SalableProduct product1 = new SalableProduct(10, "Item A", "", 0.0, "");
        SalableProduct product2 = new SalableProduct(5, "Item B", "", 0.0, "");
        assertEquals(1, product1.compareTo(product2));
        assertEquals(-1, product2.compareTo(product1));
        SalableProduct product3 = new SalableProduct(10, "Item C", "", 0.0, "");
        assertEquals(0, product1.compareTo(product3));
    }

    @Test
    public void testSalableProductGettersAndSetters() {
        SalableProduct product = new SalableProduct();
        product.setId(404);
        assertEquals(404, product.getId());
        product.setName("Updated Item");
        assertEquals("Updated Item", product.getName());
        product.setDescription("Modified description");
        assertEquals("Modified description", product.getDescription());
        product.setPrice(75.50);
        assertEquals(75.50, product.getPrice(), 0.001);
        LocalDate manufactureDate = LocalDate.of(2025, 5, 10);
        product.setDateOfManufacture(manufactureDate.format(DateTimeFormatter.ISO_DATE));
        assertEquals(manufactureDate.format(DateTimeFormatter.ISO_DATE), product.getDateOfManufacture());
    }

    @Test
    public void testSalableProductSpecialCases() {
        SalableProduct product = new SalableProduct();
        product.setName(null);
        assertNull(product.getName());
        product.setDescription(null);
        assertNull(product.getDescription());
        product.setDateOfManufacture(null);
        assertNull(product.getDateOfManufacture());
        product.setPrice(-10.0);
        assertEquals(-10.0, product.getPrice(), 0.001);
    }
}
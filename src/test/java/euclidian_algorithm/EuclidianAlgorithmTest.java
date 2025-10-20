package euclidian_algorithm;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EuclidianAlgorithmTest {

    // --- secondNum == 0 cases ---

    @Test
    public void testSecondZero_FirstPositive() {
        assertEquals(5, EuclidianAlgorithm.euclid(5, 0));
    }

    @Test
    public void testSecondZero_FirstNegative() {
        assertEquals(5, EuclidianAlgorithm.euclid(-5, 0));
    }

    @Test
    public void testBothZero() {
        assertEquals(0, EuclidianAlgorithm.euclid(0, 0));
    }

    // --- secondNum != 0, triggering recursion ---

    @Test
    public void testGCD_PositiveNumbers() {
        assertEquals(6, EuclidianAlgorithm.euclid(48, 18)); // classic example
    }

    @Test
    public void testGCD_FirstLessThanSecond() {
        assertEquals(6, EuclidianAlgorithm.euclid(18, 48));
    }

    @Test
    public void testGCD_WithOne() {
        assertEquals(1, EuclidianAlgorithm.euclid(13, 1));
        assertEquals(1, EuclidianAlgorithm.euclid(1, 13));
    }

    @Test
    public void testGCD_NegativeFirst() {
        assertEquals(4, EuclidianAlgorithm.euclid(-8, 12));
    }

    @Test
    public void testGCD_NegativeSecond() {
        assertEquals(4, EuclidianAlgorithm.euclid(8, -12));
    }

    @Test
    public void testGCD_BothNegative() {
        assertEquals(4, EuclidianAlgorithm.euclid(-8, -12));
    }

    @Test
    public void testGCD_FirstZero() {
        assertEquals(9, EuclidianAlgorithm.euclid(0, 9));
    }

    @Test
    public void testGCD_SecondZero_FirstNegative() {
        assertEquals(9, EuclidianAlgorithm.euclid(-9, 0));
    }

    @Test
    public void testGCD_SamePositiveNumbers() {
        assertEquals(7, EuclidianAlgorithm.euclid(7, 7));
    }

    @Test
    public void testGCD_SameNegativeNumbers() {
        assertEquals(7, EuclidianAlgorithm.euclid(-7, -7));
    }

    @Test
    public void testGCD_LargeNumbers() {
        assertEquals(1, EuclidianAlgorithm.euclid(1_000_000_007, 9));
    }

    @Test
    public void testGCD_MaxInt() {
        assertEquals(1, EuclidianAlgorithm.euclid(Integer.MAX_VALUE, Integer.MAX_VALUE - 1));
    }


}

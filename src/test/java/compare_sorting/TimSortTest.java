package compare_sorting;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class TimSortTest {

    private TimSort sorter;

    @BeforeEach
    public void setUp() {
        sorter = new TimSort();
    }

    // Edge Case: Empty array
    @Test
    public void testTimSort_EmptyArray() {
        int[] arr = {};
        int[] expected = {};
        int[] result = sorter.timSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // Edge Case: Single element
    @Test
    public void testTimSort_SingleElement() {
        int[] arr = {42};
        int[] expected = {42};
        int[] result = sorter.timSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // Edge Case: Two elements (sorted)
    @Test
    public void testTimSort_TwoElementsSorted() {
        int[] arr = {1, 2};
        int[] expected = {1, 2};
        int[] result = sorter.timSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // Edge Case: Two elements (unsorted)
    @Test
    public void testTimSort_TwoElementsUnsorted() {
        int[] arr = {2, 1};
        int[] expected = {1, 2};
        int[] result = sorter.timSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // Edge Case: Already sorted array
    @Test
    public void testTimSort_AlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] result = sorter.timSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // Edge Case: Reverse sorted array
    @Test
    public void testTimSort_ReverseSorted() {
        int[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] result = sorter.timSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // Edge Case: All duplicate elements
    @Test
    public void testTimSort_AllDuplicates() {
        int[] arr = {5, 5, 5, 5, 5, 5, 5};
        int[] expected = {5, 5, 5, 5, 5, 5, 5};
        int[] result = sorter.timSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // Edge Case: Array with negative numbers
    @Test
    public void testTimSort_NegativeNumbers() {
        int[] arr = {-5, 3, -1, 7, -9, 0, 4, -2};
        int[] expected = {-9, -5, -2, -1, 0, 3, 4, 7};
        int[] result = sorter.timSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // Edge Case: Array with duplicates
    @Test
    public void testTimSort_WithDuplicates() {
        int[] arr = {4, 2, 7, 2, 9, 4, 1, 7};
        int[] expected = {1, 2, 2, 4, 4, 7, 7, 9};
        int[] result = sorter.timSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // Large Array: Exactly MIN_MERGE size (32 elements)
    @Test
    public void testTimSort_ExactlyMinMergeSize() {
        int[] arr = new int[32];
        Random rand = new Random(42);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000);
        }
        int[] expected = arr.clone();
        Arrays.sort(expected);
        int[] result = sorter.timSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // Large Array: 100 elements
    @Test
    public void testTimSort_LargeArray100() {
        int[] arr = new int[100];
        Random rand = new Random(123);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10000);
        }
        int[] expected = arr.clone();
        Arrays.sort(expected);
        int[] result = sorter.timSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // Large Array: 1000 elements
    @Test
    public void testTimSort_LargeArray1000() {
        int[] arr = new int[1000];
        Random rand = new Random(456);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100000);
        }
        int[] expected = arr.clone();
        Arrays.sort(expected);
        int[] result = sorter.timSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // Large Array: 5000 elements
    @Test
    public void testTimSort_LargeArray5000() {
        int[] arr = new int[5000];
        Random rand = new Random(789);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000000);
        }
        int[] expected = arr.clone();
        Arrays.sort(expected);
        int[] result = sorter.timSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // === MergeSort Tests ===

    @Test
    public void testMergeSort_EmptyArray() {
        int[] arr = {};
        int[] expected = {};
        int[] result = sorter.mergeSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMergeSort_BasicUnsorted() {
        int[] arr = {5, 2, 8, 1, 9, 3};
        int[] expected = {1, 2, 3, 5, 8, 9};
        int[] result = sorter.mergeSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMergeSort_LargeArray() {
        int[] arr = new int[1000];
        Random rand = new Random(999);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(50000);
        }
        int[] expected = arr.clone();
        Arrays.sort(expected);
        int[] result = sorter.mergeSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    // === InsertionSort Tests ===

    @Test
    public void testInsertionSort_EmptyArray() {
        int[] arr = {};
        int[] expected = {};
        int[] result = sorter.insertionSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    @Test
    public void testInsertionSort_BasicUnsorted() {
        int[] arr = {9, 3, 7, 1, 5};
        int[] expected = {1, 3, 5, 7, 9};
        int[] result = sorter.insertionSort(arr.clone());
        assertArrayEquals(expected, result);
    }

    @Test
    public void testInsertionSort_WithNegatives() {
        int[] arr = {-3, 5, -1, 0, 2, -5};
        int[] expected = {-5, -3, -1, 0, 2, 5};
        int[] result = sorter.insertionSort(arr.clone());
        assertArrayEquals(expected, result);
    }
}

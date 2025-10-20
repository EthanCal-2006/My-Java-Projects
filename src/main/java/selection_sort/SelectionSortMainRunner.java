package selection_sort;

import java.util.Arrays;

public class SelectionSortMainRunner {
    public static void main(String[] args) {
        int[] arr1 = {10, 8, 4, 5, 100, 200, 45, 35, 67, 21};

        System.out.println("Arr1 before sorting: ");
        System.out.println(Arrays.toString(arr1));
        //printArray(arr1);
        selectionSort(arr1);
        System.out.println("Arr1 after sorting: ");
        System.out.println(Arrays.toString(arr1));
        //printArray(arr1);
    }


    public static void printArray(int[] arr) {
        for(int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for(int i = 0; i < n-1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }//innerFor
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }//outerFor
    }
}

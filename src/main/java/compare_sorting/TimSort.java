package compare_sorting;

@lombok.Data
public class TimSort {
    
    private static final int MIN_MERGE = 32;
    private int left = 0;
    private int mergeLeft = 0;
    private int mergeMid = 0;
    private int mergeRight = 0;

    public int[] timSort(int[] arr) {
        int n = arr.length;
        int minRun = minRunLength(MIN_MERGE);

        // Sort individual subarrays of size minRun using insertion sort
        for (int i = 0; i < n; i += minRun) {
            setLeft(i);
            insertionSort(arr);
        }
        System.out.println("Sorted subarrays: ");
        printArray(arr);

        // Start merging sorted runs
        for (int size = minRun; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    setMergeLeft(left);
                    setMergeMid(mid);
                    setMergeRight(right);
                    mergeSort(arr);
                }
            }
            printArray(arr);
        }

        return arr;
    }

    public int[] mergeSort(int[] arr) {
        int l = getMergeLeft();
        int m = getMergeMid();
        int r = getMergeRight();

        // If bounds are invalid (e.g. not set), sort the entire array
        if (l >= r || m < l || m >= r || r >= arr.length) {
            // fallback: full sort using standard merge sort logic
            return fullMergeSort(arr, 0, arr.length - 1);
        }

        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];

        for (int x = 0; x < len1; x++) {
            left[x] = arr[l + x];
        }
        for (int x = 0; x < len2; x++) {
            right[x] = arr[m + 1 + x];
        }

        int i = 0, j = 0, k = l;

        while (i < len1 && j < len2) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < len1) {
            arr[k++] = left[i++];
        }

        while (j < len2) {
            arr[k++] = right[j++];
        }
        return arr;
    }

    public int[] insertionSort(int[] arr) {
        int n = arr.length;
        int left = getLeft();
        int right = Math.min((left + MIN_MERGE - 1), (n - 1));
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
        return arr;
    }

    public static int minRunLength(int n)
    {
        assert n >= 0;

        // Becomes 1 if any 1 bits are shifted off
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    //* fullMergeSort and merge are helper methods for mergeSort when the array is less than
    //* the MIN_MERGE value, so these methods will sort the array with a new MIN_MERGE value
    //* defined by the middle index of the array so that it can still merge sort
    private int[] fullMergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            fullMergeSort(arr, left, mid);
            fullMergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
        return arr;
    }

    private void merge(int[] arr, int l, int m, int r) {
        int len1 = m - l + 1;
        int len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];

        for (int x = 0; x < len1; x++) {
            left[x] = arr[l + x];
        }
        for (int x = 0; x < len2; x++) {
            right[x] = arr[m + 1 + x];
        }

        int i = 0, j = 0, k = l;
        while (i < len1 && j < len2) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < len1) {
            arr[k++] = left[i++];
        }

        while (j < len2) {
            arr[k++] = right[j++];
        }
    }

    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1 && arr[i] > arr[i + 1]) {
                System.out.print(arr[i] + " | ");
            } else {
                System.out.print(arr[i]);
                if (i < arr.length - 1) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println("]");
    }

}

package hash_map;

import java.util.HashMap;
import java.util.Map;

public class CountSubArray {
    static void main() {
        int[] nums = {1, 1, 1};
        int k = 2;
        System.out.println("Count of subarrays with sum " + k + ": " + countSubArrays(nums, k));

        int[] nums2 = {3, 4, 7, 2, -3, 1, 4, 2};
        int k2 = 7;
        System.out.println("Count of subarrays with sum " + k2 + ": " + countSubArrays(nums2, k2));
    }

    public static int countSubArrays(int[] nums, int k) {
        return countHelper(nums, 0, nums.length - 1, k);
    }

    public static int countHelper(int[] nums, int left, int right, int k) {
        if (left > right) return 0;

        if (left == right) {
            return nums[left] == k ? 1 : 0;
        }

        int mid = (left + right) / 2;

        //? count in left and right halves
        int count = countHelper(nums, left, mid, k) + countHelper(nums, mid + 1, right, k);

        //? use this to count the subarrays that cross the middle index
        Map<Integer, Integer> rightPrefixMap = new HashMap<>();
        int sum = 0;

        //? build the prefix sum for the right half
        for(int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightPrefixMap.put(sum, rightPrefixMap.getOrDefault(sum, 0) + 1);
        }

        sum = 0;

        //? go left and look for complement sums to the prefix right sums
        for (int i = mid; i >= left; i--) {
            sum += nums[i];

            if (sum == k) count++; //? subarray from i to mid index only

            int complement = k - sum;
            if(rightPrefixMap.containsKey(complement)) {
                count += rightPrefixMap.get(complement);
            }
        }
        return count;
    }
}

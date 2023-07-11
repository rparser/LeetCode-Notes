import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int now = 1, times = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            now *= nums[right];

            while (now >= k)
                now /= nums[left++];

            times += right - left + 1;
        }
        return times;
    }

    public static List<List<Integer>> SubarrayProductLessThanK(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int product = 1, left = 0;
        for (int right = 0; right < arr.length; right++) {
            product *= arr[right];
            while (product >= target && left < arr.length)
                product /= arr[left++];
            // since the product of all numbers from left to right is less than the target therefore,
            // all subarrays from left to right will have a product less than the target too; to avoid
            // duplicates, we will start with a subarray containing only arr[right] and then extend it
            List<Integer> tempList = new LinkedList<>();
            for (int i = right; i >= left; i--) {
                tempList.add(0, arr[i]);
                result.add(new ArrayList<>(tempList));
            }
        }
        return result;
    }
}

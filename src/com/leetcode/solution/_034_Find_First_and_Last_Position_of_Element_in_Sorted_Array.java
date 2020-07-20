package com.leetcode.solution;

/**
 * Time complexity : O(logN)
 * Because binary search cuts the search space roughly in half on each iteration, there can be at most logN iterations.
 * Binary search is invoked twice, so the overall complexity is logarithmic.
 * Space complexity : O(1)
 * All work is done in place, so the overall memory usage is constant.
 */

public class _034_Find_First_and_Last_Position_of_Element_in_Sorted_Array {

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};
        int leftIdx = insertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target)
            return targetRange;
        //先找到左侧再找到右侧
        targetRange[0] = leftIdx;
        targetRange[1] = insertionIndex(nums, target, false) - 1; //求出最右侧(left=false)

        return targetRange;
    }

    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    // if left is true, then we "recurse" on the left subarray on ties. Otherwise, we go right.
    private int insertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;
        //hi取不到
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            //如果中值大于目标值 或 (中值为目标值 AND left为true），target在左侧
            if (nums[mid] > target || (left && target == nums[mid]))
                hi = mid;
            else
                lo = mid + 1; //否则target在右侧
        }
        return lo;
    }
}

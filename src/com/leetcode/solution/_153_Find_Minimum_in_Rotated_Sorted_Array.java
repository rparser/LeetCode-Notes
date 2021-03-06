package com.leetcode.solution;


/**
 * 33子问题，找最小值（翻转点）
 * Complexity: Time O(logN)
 * Space O(1)
 */

public class _153_Find_Minimum_in_Rotated_Sorted_Array {
    // O(LogN),O(1)
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return nums[0];

        int low = 0;
        int high = n - 1;
        // 第一个值 < 最后一个值则不存在翻转
        // 如果反转了最后一个值是第一个值的上一个值！
        if (nums[low] < nums[high])
            return nums[0];
        // 翻转点的下一个值为最小值
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[mid + 1])
                return nums[mid + 1]; //是翻转点
            if (nums[mid] < nums[low])
                high = mid - 1; //翻转点在左侧
            else
                low = mid + 1; //翻转点在右侧
        }
        return nums[0];
    }
}

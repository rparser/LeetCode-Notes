package com.leetcode.solution;

/**
 * 二分搜索在翻转后的排序数组（存在重复值）
 * 033为不存在重复值
 * <p>
 * Time complexity : O(log(N)).
 * Space complexity : O(1).
 * <p>
 * 先判断mid和low关系再if target和mid和low关系
 */

public class _081_Search_in_Rotated_Sorted_Array_II {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target)
                return true;
            // 排除重复值
            if (nums[mid] == nums[low])
                low++;
            // 先判断mid和low关系再if target和mid和low关系
            // 否则如果mid > low，即翻转在后半部分
            else if (nums[mid] > nums[low]) {
                if (target < nums[mid] && target >= nums[low])
                    high = mid - 1; // 如果target值，在low和mid间，则在前半部分找（已排序好）
                else
                    low = mid + 1; // 否则在后半部分找
                // 否则如果mid < low，即翻转在前半部分
            } else {
                if (target > nums[mid] && target <= nums[high])
                    low = mid + 1; // 如果target值，在mid和high间，则在后半部分找（已排序好）
                else
                    high = mid - 1;    // 否则在前半部分找
            }
        }
        return false;
    }
}

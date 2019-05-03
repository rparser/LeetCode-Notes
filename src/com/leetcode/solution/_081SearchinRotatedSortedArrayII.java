package com.leetcode.solution;

/**
 * 二分搜索在翻转后的排序数组（存在重复值）
 * 033为不存在重复值
 * <p>
 * Time complexity : O(log(N)).
 * Space complexity : O(1).
 */

public class _081SearchinRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] == nums[low]) low++;  // 如果low-mid均为重复值
            else if (nums[mid] > nums[low]) { // 否则如果mid > low，即翻转在后半部分
                if (target < nums[mid] && target >= nums[low]) high = mid - 1; // 如果target值，在low和mid间，则在前半部分找（已排序好）
                else low = mid + 1; // 否则在后半部分找
            } else { // 否则如果mid < low，即翻转在前半部分
                if (target > nums[mid] && target <= nums[high]) low = mid + 1; // 如果target值，在mid和high间，则在后半部分找（已排序好）
                else high = mid - 1;    // 否则在前半部分找
            }
        }
        return false;
    }
}
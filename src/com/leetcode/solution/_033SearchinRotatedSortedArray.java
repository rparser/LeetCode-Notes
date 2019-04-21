package com.leetcode.solution;

/**
 * 二分搜索在翻转后的排序数组（不存在重复值）
 * 081为存在重复值
 * <p>
 * Time complexity : O(log(N)).
 * Space complexity : O(1).
 */

public class _033SearchinRotatedSortedArray {
    int[] nums;
    int target;

    public int find_rotate_index(int low, int high) {
        if (nums[low] < nums[high]) return 0; // 如果不存在翻转
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] > nums[mid + 1]) return mid + 1;
            if (nums[mid] < nums[low]) high = mid - 1;
            else low = mid + 1;
        }
        return 0;
    }

    // 二分搜索，035题
    public int binarySearch(int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return this.nums[0] == target ? 0 : -1;

        int rotate_index = find_rotate_index(0, n - 1); // 找到翻转点（最小值）

        if (rotate_index == 0) return binarySearch(0, n - 1); // 如果不存在翻转则二分搜索
        if (nums[rotate_index] == target) return rotate_index; // 如果target是翻转点则返回翻转点
        if (target < nums[0]) return binarySearch(rotate_index, n - 1);// 如果target小于第一个值，则在翻转后部搜索
        return binarySearch(0, rotate_index); // 否则在翻转前部搜索
    }
}

package com.leetcode.solution;

/**
 * 二分搜索在翻转后的排序数组（不存在重复值）
 * 081为存在重复值
 * <p>
 * Complexity: Time O(logN)
 * Space O(1)
 * <p>
 * 先找二分翻转点，四情况：无翻转，翻转点为目标，右侧(target < nums[0])，左侧
 */

public class _033_Search_in_Rotated_Sorted_Array {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target)
                return mid;
            // 排除重复值,如果无重复值不需要这一句
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
        return -1;
    }


    private int[] nums;
    private int target;

    // 二分搜索，035题
    private int binarySearch(int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    private int find_rotate_index(int low, int high) {
        if (nums[low] < nums[high]) return 0; // 第一个值小于最后一个值则不存在翻转
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[mid + 1]) return mid + 1; //是翻转点
            if (nums[mid] < nums[low]) high = mid - 1; //翻转点在左侧
            else low = mid + 1; //翻转点在右侧
        }
        return 0;
    }

    public int searchSlow(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return this.nums[0] == target ? 0 : -1;

        int rotate_index = find_rotate_index(0, n - 1); // 找到翻转点（最小值）

        if (rotate_index == 0) return binarySearch(0, n - 1); // 如果不存在翻转则正常二分搜索
        if (nums[rotate_index] == target) return rotate_index; // 如果target是翻转点则返回翻转点
        if (target < nums[0]) return binarySearch(rotate_index, n - 1);// 如果target小于第一个值，则在翻转后部搜索
        return binarySearch(0, rotate_index); // 否则在翻转前部搜索
    }
}

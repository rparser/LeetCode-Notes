package com.leetcode.solution;

/**
 * 参考 https://imageslr.com/2020/03/15/binary-search.html
 */
public class _704_Binary_Search {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) { // (只有l>r时，完全脱离，才会跳出循环)
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
}


package com.leetcode.solution;

/**
 * 循环体内有2个分支
 * 在循环体外返回目标索引，在循环体内缩减搜索区间
 * O(logn)
 */
public class _852_Peak_Index_in_a_Mountain_Array {
    public int peakIndexInMountainArray(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 缩减区间为[mid+1,right]
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            }
            // 缩减区间为[left,mid]
            else {
                right = mid;
            }
        }
        // left=right时退出循环，返回left或right是一样的
        return left;

    }
}

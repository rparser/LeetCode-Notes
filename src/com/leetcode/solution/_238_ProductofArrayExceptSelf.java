package com.leetcode.solution;

/**
 * 双向遍历法
 * 思路：Iteration from two side.
 * Use left(skip 1st element), right(skip last element) to track product of array.
 * left遍历->自身以左的，right遍历<-自身以右的
 */

public class _238_ProductofArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        if (nums.length <= 1) return nums;
        int[] result = new int[nums.length];
        result[0] = 1;
        int left = 1, right = 1;
        //left product, skip fist ele (1),left遍历->自身以左的
        for (int i = 1; i < nums.length; i++) {
            left = nums[i - 1] * left;
            result[i] = left;
        }
        //right product, skip last ele,right遍历<-自身以右的
        for (int i = nums.length - 2; i >= 0; i--) {
            right = nums[i + 1] * right;
            result[i] *= right;
        }
        return result;
    }
}

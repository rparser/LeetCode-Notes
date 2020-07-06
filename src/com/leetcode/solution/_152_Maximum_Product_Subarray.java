package com.leetcode.solution;

/**
 * subarray最大乘积
 * 依次取，考虑到负数分别取max * nums[i], min * nums[i], nums[i]的最大值（同时计算最小值）
 */

public class _152_Maximum_Product_Subarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int max = nums[0], min = nums[0], result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int temp = max; // temp很重要因为计算max后max可能变动,比如[-3,-2,-1]，没有temp会输出12
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]); //考虑到负数，所以为max * nums[i], min * nums[i], nums[i]的最大值
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]); //同为三者最小值
            if (max > result)
                result = max;
        }
        return result;
    }
}

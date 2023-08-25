package com.leetcode.solution;

import java.util.Arrays;

/**
 * 前缀和
 */

public class _2090_K_Radius_Subarray_Averages {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        if (n < k || n == 0) {
            return new int[]{-1};
        }
        int[] result = new int[n];
        Arrays.fill(result, -1);
        for (int i = k; i < n - k; i++) {
            int left = i - k;
            int right = i + k;
            int standard = nums[i];
            int sum = 0;
            while (left < right) {
                sum += (nums[left] - standard) + (nums[right] - standard);//算下基准线的差值
                left++;
                right--;
            }
            result[i] = (int) (standard + sum / (double) (2 * k + 1));
        }
        return result;
    }
}

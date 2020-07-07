package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

public class _209_Minimum_Size_Subarray_Sum {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        int result = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;
        int curr_sum = 0;

        while (right < len) {
            //先找到>=s的，如果还没找到，则right向后移一位，且在sum更新
            while (curr_sum < s && right < len) {
                curr_sum += nums[right];
                right++;
            }
            //找到后,left向右以排除看是否还大于
            while (curr_sum >= s && left < len) {
                // 更新result
                result = Math.min(right - left, result);
                curr_sum -= nums[left];//start要向后移来缩小res大小
                left++;
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._209MinimumSizeSubarraySum");
    }

    @Test
    public void testSolution() {
        int[] input = {1, 2, 3};
        Assert.assertEquals(2, minSubArrayLen(2, input));
    }
}

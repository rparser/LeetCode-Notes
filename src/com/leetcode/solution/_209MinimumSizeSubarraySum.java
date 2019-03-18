package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

public class _209MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        int start = 0;
        int end = 0;
        int curr_sum = 0;
        int res = nums.length + 1;
        while (end < nums.length) {
            while (curr_sum < s && end < nums.length) curr_sum += nums[end++]; //如果sum小于要求值，则end向后移一位，且在sum更新+
            while (curr_sum >= s && start < nums.length) {     //当sum大于要求值
                if (end - start < res) res = end - start;//如果新的res比之前的小，则更新
                curr_sum -= nums[start++];//start要向后移来缩小res大小
            }
        }
        return res > nums.length ? 0 : res;
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

package com.leetcode.solution;

public class _268_MissingNumber {
    // Solution 1: Sum
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int result = (0 + n) * (n + 1) / 2; //应该得到的值
        for (int i : nums)
            result -= i;
        return result;
    }

    // Solution 2: XOR
    public int missingNumberXOR(int[] nums) {
        int n = nums.length;
        int x = 0;
        for (int i = 1; i <= n; ++i)
            x = x ^ i ^ nums[i - 1]; //异或自己为0
        return x;
    }
}

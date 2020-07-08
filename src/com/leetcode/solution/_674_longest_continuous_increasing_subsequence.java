package com.leetcode.solution;

class _674_longest_continuous_increasing_subsequence {
    // O(N), O(1)
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length < 1)
            return 0;
        int curLen = 0, result = 0, prev = Integer.MIN_VALUE;
        for (int cur : nums) {
            if (cur > prev) {
                prev = cur;
                curLen++;
                result = Math.max(curLen, result);
            } else {
                prev = cur;
                curLen = 1;
            }
        }
        return result;
    }
}
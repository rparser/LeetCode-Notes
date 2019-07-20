package com.leetcode.solution;

/**
 * Time complexity : O(n). Assume that nn is the number of houses, the time complexity is O(n).
 * Space complexity : O(1)
 */

public class _198_HouseRobber {
    public int rob(int[] nums) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : nums) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }
}

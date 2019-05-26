package com.leetcode.solution;

/**
 * dp
 */

public class _416_PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        // check edge case
        if (nums == null || nums.length == 0) {
            return true;
        }
        // preprocess
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        // dp def
        boolean[] dp = new boolean[sum+1]; //和等于sum是否有可能
        // dp init
        dp[0] = true; //和为0是可能
        // dp transition
        for (int i = 1; i <= nums.length; i++) {
            for (int j = sum; j >= nums[i-1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i-1]]; //dp[j-nums[i-1]]是指不考虑nums[i-1]时就可以为true
            }
        }
        return dp[sum];
    }
}

package com.leetcode.solution;

//dp[2][n] = max(dp[2][n-1], dp[1][n-k] + sumRange(n, n -k+1))
class _689_maximum_sum_of_3_non_overlapping_subarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        // 到j位置的k个最大子数组和
        int[][] dp = new int[k][nums.length];
        int[] cumulative = new int[nums.length];
        int sum = 0;
        // 前缀和
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            cumulative[i] = sum;
        }

        for (int i = 0; i < k; i++)
            for (int j = 0; j < nums.length; j++)
                if (j < (i + 1) * k - 1)
                    dp[i][j] = 0;
                else {
                    if (i == 0)
                        // 易错点: 当k=1的时候，边界条件需要处理一下。
                        dp[i][j] = Math.max(j > 0 ? dp[i][j - 1] : 0, rangeSum(cumulative, j - k + 1, j));
                    else
                        dp[i][j] = Math.max(j > 0 ? dp[i][j - 1] : 0
                                , rangeSum(cumulative, j - k + 1, j) + dp[i - 1][j - k]);
                }

        int[] ans = new int[k];
        int length = dp[2].length - 1;
        for (int i = 2; i >= 0; i--) {
            int[] row = dp[i];
            for (int j = length - 1; j >= 0; j--)
                if (row[j] != row[length]) {
                    ans[i] = j - k + 2;
                    length = j - k + 1;
                    break;
                }
        }
        return ans;
    }

    private int rangeSum(int[] cumulative, int left, int right) {
        if (left == 0)
            return cumulative[right];
        else
            return cumulative[right] - cumulative[left - 1];
    }
}
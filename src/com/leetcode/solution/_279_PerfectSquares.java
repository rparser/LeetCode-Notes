package com.leetcode.solution;

import java.util.Arrays;

/**
 * DP,多少个square可以组成
 * <p>
 * 2loop(1~n)(i-j*j>=0)，内层min = min(min, dp[i - j * j] + 1);dp[i]=min;
 */

public class _279_PerfectSquares {
    public int numSquares(int n) {
        //dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            while (i - j * j >= 0) {
                min = Math.min(min, dp[i - j * j] + 1); //更新min
                j++;
            }
            dp[i] = min;
        }
        return dp[n];
    }
}

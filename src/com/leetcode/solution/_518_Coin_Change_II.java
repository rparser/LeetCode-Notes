package com.leetcode.solution;

class _518_Coin_Change_II {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coin];
            }
        }
        return dp[dp.length - 1];
    }
}

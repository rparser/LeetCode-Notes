package com.leetcode.solution;

/**
 * DP换零钱
 * 思路：-  use dp bottom up, memoization method
 * - we know each solution is formed by at least 1 existing coin, so we check if dp[i-coin] exists.
 * - keep filling dp[i] with the min
 * Complexity: Time O(m*n) -  m is amount, n is len of coins,  Space O(m) - dp array
 * <p>
 * 单array操作， 根据上一次的运算，来fill 一个array, return the last element of array
 * DP,2loop(0-amount,coin),count=-1,i>=coin&dp[i-coin]!=-1上一步可实现，则cur=上一步+1，如果count==-1||cur<count,更新count=cur再赋给dp
 */

public class _322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; //0,1,2,..,amount
        for (int i = 1; i <= amount; i++) {
            int count = -1;
            //key is min count, all solutions must be generated from existing coin
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != -1) { //skip non-existing solution
                    int cur = dp[i - coin] + 1; //重点，前一个+1
                    if (count == -1 || count > cur) count = cur; //如果之前无法达到，或当前cur更小
                }
            }
            dp[i] = count; //如果最后不能达到，会把-1加入dp,否则加入cur
        }
        return dp[amount];
    }
}

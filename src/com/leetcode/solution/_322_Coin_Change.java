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

public class _322_Coin_Change {
    // bottom up DP, O(m*n), O(m)
    public int coinChange(int[] coins, int amount) {
        // dp数组是当前位置最小的硬币数, 0为空
        int[] dp = new int[amount + 1]; //0,1,2,..,amount

        for (int curAmount = 1; curAmount <= amount; curAmount++) {
            int count = -1; // 无法完成
            //key is min count, all solutions must be generated from existing coin
            for (int coin : coins) {
                // 必须不能是不可完成dp[i - coin] == -1（因为dp[i-coin]如果不可完成 那dp[i-coin+coin]也不可完成了）
                if (curAmount >= coin && dp[curAmount - coin] != -1) {
                    //重点，前一个+1,因为多了一个硬币
                    int curCount = dp[curAmount - coin] + 1;
                    // 在此coin for loop里，count会被更新到最小的cur
                    if (count == -1 || curCount < count) {
                        count = curCount; // 更新count - 只有在符合if才更新
                    }
                }
            }
            dp[curAmount] = count; //如果最后不能达到，会把-1加入dp,否则加入cur(最小的count)
        }
        return dp[amount];
    }
}

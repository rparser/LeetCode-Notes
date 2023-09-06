package com.leetcode.solution;

/**
 * 典型的动态规划，并且利用局部和全局最优，这种思想值得仔细体会，好好掌握。
 * 这种局部最优与全局最优问题
 * global[i][j] = max(local[i][j], global[i - 1][j])
 * 很容易知道上面这个递推式。
 * 关键在于求取局部最优的递推式，在本题中
 * local[i][j] = max(global[i - 1][j - 1] + max(diff, 0), local[i - 1][j] + diff)
 * 这里我们需要两个递推公式来分别更新两个变量local和global
 * 我们定义local[i][j]为在到达第i天时最多可进行j次交易并且最后一次交易在最后一天卖出的最大利润，此为局部最优。
 * 然后我们定义global[i][j]为在到达第i天时最多可进行j次交易的最大利润，此为全局最优
 * 局部最优解的递推式可以这样理解：
 * <p>
 * 在i-1天时，正在进行第j次交易，所以我们最后一天必须将股票卖出，而且这是算在第j次交易当中的，
 * 这种情况下，local[i - 1][j] + diff，只需将i-1天的局部最优解加上最后一天卖出的差值即可，相当于将最后一次交易多延迟了一天
 * 第二种情况，就是在第i-1天进行j-1次交易的全局最优解，我们在最后一天还得进行一次交易，必须卖出，
 * 如果diff大于0，那么就在第i-1天买进，i天卖出，如果小于0，就直接在第i天买进又卖出，只是为了满足j次交易，
 * 就相当于没有交易，即加上0就可以了。
 */

class _188_Best_Time_to_Buy_and_Sell_Stock_IV {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k <= 0 || len < 2) {
            return 0;
        }
        int maxProfit = 0;

        if (k >= len / 2) {
            //直接当作贪心处理，也就是问题2的答案,可以无限次
            for (int i = 1; i < len; i++) {
                int diff = prices[i] - prices[i - 1];
                if (diff > 0) {
                    maxProfit += diff;
                }
            }
            return maxProfit;
        }

        int[][] local = new int[len][k + 1];
        int[][] global = new int[len][k + 1];

        for (int i = 1; i < len; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) { //统计次数
                local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(diff, 0),
                        local[i - 1][j] + diff);
                global[i][j] = Math.max(local[i][j], global[i - 1][j]);
            }
        }
        return global[len - 1][k];
    }
}

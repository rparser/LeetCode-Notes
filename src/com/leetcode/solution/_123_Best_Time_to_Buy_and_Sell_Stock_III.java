package com.leetcode.solution;

public class _123_Best_Time_to_Buy_and_Sell_Stock_III {
    //finite-state machine O(N),O(1)
    public int maxProfit(int[] prices) {
        int minPrice1 = Integer.MAX_VALUE;
        int maxProfit1 = 0; //max profit after 1st sell
        // 假设今天第二次买入：更新 第二次买后的最大剩余利润
        int maxProfitAfterBuy = Integer.MIN_VALUE;
        // 假设今天第二次卖出：更新 当天能获得的最大利润
        int maxProfit2 = 0;
        for (int price : prices) {
            // 1.第一次最小购买价格
            minPrice1 = Math.min(minPrice1, price);

            // 2.第一次卖出的最大利润
            maxProfit1 = Math.max(maxProfit1, price - minPrice1);

            // 3.第二次购买后的剩余净利润
            maxProfitAfterBuy = Math.max(maxProfitAfterBuy, maxProfit1 - price);

            // 4.第二次卖出后，总共获得的最大利润（第3步的净利润 + 第4步卖出的股票钱）
            maxProfit2 = Math.max(maxProfit2, price + maxProfitAfterBuy);
        }
        return maxProfit2;
    }
}
package com.leetcode.solution;

class _122_Best_Time_to_Buy_and_Sell_Stock_II {
    //买卖多次 O(N), O(1)
    public int maxProfit(int[] prices) {
        int result = 0, prev = Integer.MAX_VALUE;
        for (int i : prices) {
            if (i > prev) {
                //大于则加入结果再更新
                result += (i - prev);
            }
            prev = i;
        }
        return result;
    }
}
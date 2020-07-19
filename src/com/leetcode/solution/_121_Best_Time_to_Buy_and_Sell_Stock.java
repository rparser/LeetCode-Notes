package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

public class _121_Best_Time_to_Buy_and_Sell_Stock {

    /**
     * 购买股票,单项数组
     * <p>
     * Time complexity : O(n). Only a single pass is needed.
     * Space complexity : O(1). Only two variables are used.
     */
    // 买卖一次
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; //minPrice初始化为最大值
        int maxProfit = 0;
        for (int price : prices) {
            //如果比最小值小 则为下跌区间
            if (price < minPrice)
                minPrice = price;
                //else为上涨区间 有可能出现最大值
            else if (price - minPrice > maxProfit)
                maxProfit = price - minPrice;
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._121BestTimetoBuyandSellStock");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(true, true);
    }

}

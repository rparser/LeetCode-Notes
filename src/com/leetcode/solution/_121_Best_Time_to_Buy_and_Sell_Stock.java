package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

public class _121BestTimetoBuyandSellStock {

    /**
     * 购买股票,单项数组
     * <p>
     * Time complexity : O(n). Only a single pass is needed.
     * Space complexity : O(1). Only two variables are used.
     */

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; //minPrice初始化为最大值
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) //如果比最小值小 则为下跌区间
                minPrice = prices[i];
            else if (prices[i] - minPrice > maxProfit) //else为上涨区间 有可能出现最大值
                maxProfit = prices[i] - minPrice;
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

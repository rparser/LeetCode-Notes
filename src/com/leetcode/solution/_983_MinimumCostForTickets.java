package com.leetcode.solution;

/**
 * 最少车票问题,参考322
 * dp[i] := min cost to cover the i-th day
 * dp[0] = 0
 * dp[i] = min(dp[i – 1] + costs[0], dp[i – 7] + costs[1], dp[i – 30] + costs[2])
 * DP模板,bool[]存用的日期，loop(day1-365),如果bool为F则和dp和前值相同，dp值为min(dp[day-1/7/30]+cost[1/7/30]),返回dp[365]
 */

public class _983_MinimumCostForTickets {
    public int mincostTickets(int[] days, int[] costs) {
        boolean[] dayIncluded = new boolean[366];
        for (int day : days) dayIncluded[day] = true;

        int[] minCost = new int[366];
        minCost[0] = 0;
        for (int day = 1; day <= 365; ++day) {
            if (!dayIncluded[day]) { //如果不需要用
                minCost[day] = minCost[day - 1]; //则价格和前一天相同
                continue;
            }
            minCost[day] = Math.min(Math.min(
                    minCost[day - 1] + costs[0],
                    minCost[Math.max(0, day - 7)] + costs[1]),
                    minCost[Math.max(0, day - 30)] + costs[2]); //当前价格为1,7,30的最小值+对应cost
        }
        return minCost[365];
    }
}

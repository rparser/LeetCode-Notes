package com.leetcode.solution;

import java.util.Arrays;

public class _1029_Two_City_Scheduling {
    public int twoCitySchedCost(int[][] costs) {
        int t = costs.length;
        int[] cha = new int[t];
        int result = 0;
        for (int i = 0; i < t; i++) {
            cha[i] = costs[i][1] - costs[i][0];//计算人员到A城与到B城的消耗差
            result += costs[i][0];//加上所有人到A城的费用
        }
        Arrays.sort(cha);
        for (int i = 0; i < t / 2; i++)
            result += cha[i];//减去应到B城的人员的额外消耗
        return result;
    }
}

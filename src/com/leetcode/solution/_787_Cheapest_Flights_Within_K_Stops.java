package com.leetcode.solution;

import java.util.Arrays;

/*
 * dp[k][i]表示经过k个中转站到达目的地i所花费的最小费用
 * 初始化i=src，flight[0]当前出发城市 flight[1]目的地城市
 * 当flight[1]==src时，即从src出发,经过0个中转站到达目的地src,dp[k][src]=0,所花费费用为0
 * 当k=0,flight[0]==src,即从src出发,经过0个中转站到达目的地flight[1]所花费的费用，即src直达某地，
 * 此时花费的费用为flight[2]
 */

class _787_Cheapest_Flights_Within_K_Stops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[K + 1][n];
        for (int i = 0; i <= K; ++i)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        //初始化src可直达的班次
        for (int[] flight : flights) {
            if (flight[0] == src) {
                dp[0][flight[1]] = flight[2];
            }
        }
        //初始化数组中`dst == src`的行
        for (int i = 0; i <= K; i++) {
            dp[i][src] = 0;
        }
        for (int i = 1; i <= K; i++) {
            for (int[] flight : flights) {
                //如果经过i-1次中转可以到达某一个出发的地方
                //那么再经过一次中转就可以到达从该地方出发到达的目的地
                //比如(1,2)从1到达2，如果连1都到达不了，就不可能到达2
                //到达1之后再经过一次中转就可以到达2
                if (dp[i - 1][flight[0]] != Integer.MAX_VALUE) {
                    //类似于直达和非直达的区别
                    //当dp[i][flight[1]]原本等于Integer.MAX_VALUE
                    //说明无法直达，此时非直达通过Math.min也得到了该得到的结果
                    //如果dp[i][flight[1]]原本有值，说明可以直达，然后就比较直达和非直达所花费的钱的多少
                    dp[i][flight[1]] = Math.min(dp[i][flight[1]], dp[i - 1][flight[0]] + flight[2]);
                }
            }
        }
        return dp[K][dst] == Integer.MAX_VALUE ? -1 : dp[K][dst];
    }
}

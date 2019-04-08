package com.leetcode.common;

//public class dpDict {
//    public int dpDict(int[] dict) {
//        int m = dict.length;
//        int[][] dp = new int[m + 1][n + 1]; //考虑可能长度为零，需要+1
//        for (int i = 0; i <= m; i++) {
//            for (int j = 0; j <= n; j++) {
//                if (i == 0 || j == 0) dp[i][j] = 0;
//                else {
//                    dp[i][j] = (word1.charAt(i - 1) == word2.charAt(j - 1)) ?
//                            dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
//                }
//            }
//        }
//        int val = dp[m][n]; //最长子串长度
//        return dp[0][n]; //长度和减去两个最长子串，即为所需的步数
//}

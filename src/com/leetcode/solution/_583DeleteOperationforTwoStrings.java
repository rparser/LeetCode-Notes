package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class _583DeleteOperationforTwoStrings {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1]; //考虑可能长度为零，需要+1
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
                else {
                    dp[i][j] = (word1.charAt(i - 1) == word2.charAt(j - 1)) ?
                            dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int val = dp[m][n]; //最长子串长度
        return m + n - 2 * val; //长度和减去两个最长子串，即为所需的步数
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._583DeleteOperationforTwoStrings");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(true, true);
    }
}

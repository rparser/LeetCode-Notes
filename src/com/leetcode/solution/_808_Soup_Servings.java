package com.leetcode.solution;

/**
 *
 * 首先，每种操作分配的汤都是 25 的倍数，所以我们可以把 25 当作单位 1 来进行计算，这样dp数组会占用更少的空间。
 */
public class _808_Soup_Servings {
    static double[][] dp;

    public static double soupServings(int n) {
        if (n > 4450) {
            return 1;
        }

        n = (int) Math.ceil(n / 25d);

        dp = new double[n + 1][n + 1];

        return dfs(n, n);
    }

    public static double dfs(int a, int b) {
        if (a <= 0 && b <= 0) {
            return 0.5;
        } else if (a <= 0) {
            return 1;
        } else if (b <= 0) {
            return 0;
        }

        if (dp[a][b] == 0) {
            dp[a][b] = 0.25 * (dfs(a - 4, b) + dfs(a - 3, b - 1) + dfs(a - 2, b - 2) + dfs(a - 1, b - 3));
        }

        return dp[a][b];
    }
}

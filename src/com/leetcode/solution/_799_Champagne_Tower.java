package com.leetcode.solution;


/**
 * 定义 f[i][j] 为第 i 行第 j 列杯子所经过的水的流量（而不是最终剩余的水量）
 */
public class _799_Champagne_Tower {
    public double champagneTower(int k, int n, int m) {
        double[][] f = new double[n + 2][n + 2]; // 需要+2，因为下面有i+1
        f[0][0] = k;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (f[i][j] <= 1) { // 不超过1时不更新，因为没溢出
                    continue;
                }
                f[i + 1][j] += (f[i][j] - 1) / 2; // 下一层j溢出来一半
                f[i + 1][j + 1] += (f[i][j] - 1) / 2; // 下一层j+1溢出来一半
            }
        }
        return Math.min(f[n][m], 1);
    }
}

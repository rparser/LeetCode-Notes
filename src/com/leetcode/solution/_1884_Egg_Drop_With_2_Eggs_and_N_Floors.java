package com.leetcode.solution;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

public class _1884_Egg_Drop_With_2_Eggs_and_N_Floors {
    public int twoEggDrop(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {//假设当前的总层数
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {//尝试扔下的层数
                min = Math.min(Math.max(dp[i - j] + 1, j), min);
            }
            dp[i] = min;//总共i层高的楼的最小操作次数
        }
        return dp[n];
    }

    //O1解法
    //大致的想法就是我们要找到让公式1 + 2 + 3 + 4 + .... + x >= n成立的最小的x就是最后找到f的最小次数。
    // 所以也就是首项为1， 公差为1的等差数列的求和公式(x * (x + 1) / 2) >= n，然后解方程，求出x。
    int twoEggDropO1(int n) {
        return (int) ceil((-1. + sqrt(1 + 8 * n)) / 2.);
    }

}

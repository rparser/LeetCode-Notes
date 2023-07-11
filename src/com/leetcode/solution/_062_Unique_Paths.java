package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

/**
 * 动态规划，求左上走到右下的可能
 * <p>
 * Time Complexity: O(M×N). The rectangular grid given to us is of size M×N and we process each cell just once.
 * Space Complexity: O(MXN).
 */

public class _062_Unique_Paths {
    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n]; //二维数组保存每点的可能
        // //任何0,x或x,0都只有一种可能：一个方向走
        for (int i = 0; i < m; i++)
            res[i][0] = 1;
        for (int i = 0; i < n; i++)
            res[0][i] = 1;
        //每点的可能性为左边点可能性+上边点可能性
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                res[i][j] = res[i - 1][j] + res[i][j - 1];


        return res[m - 1][n - 1];
    }

    public int uniquePathsCombination(int m, int n) {
        double count = 1;
        int min = Math.min(m, n); //取小值
        // 因为m*n 实际是只能走m-1 和 n-1步，所以是C(m-1 + n -1 , min -1)
        for (int i = 1; i <= min - 1; i++)
            count = count * (m + n - i - 1) / i;//先乘再除，乘从后计算，除从前计算

        return (int) count;
    }
    //Permutation排列 Combination组合
    //组合C(n,k)=n!/k!(n-k)!
    //for example C(52,5)=52*51*50*49*48/(5*4*3*2*1)

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._062UniquePaths");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(3, uniquePaths(3, 2));
        Assert.assertEquals(3, uniquePathsCombination(3, 2));
    }
}

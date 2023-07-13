package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

/**
 * 动态规划，相比62为有障碍物的左上到右下
 * <p>
 * Time Complexity: O(M×N). The rectangular grid given to us is of size M×N and we process each cell just once.
 * Space Complexity: O(1). We are utilizing the obstacleGrid as the DP array. Hence, no extra space.
 */

public class _063_Unique_Paths_II {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1) //null or 0 row or 0 column
            return 0;
        int m = obstacleGrid.length; //how many rows
        int n = obstacleGrid[0].length; //how many columns

        obstacleGrid[0][0] = 1; //节省空间，利用同一个数组计算，如果起点非1则起点有1种走法（不动）
        for (int i = 1; i < m; i++)
            obstacleGrid[i][0] = obstacleGrid[i][0] == 1 ? 0 : obstacleGrid[i - 1][0]; //最左一列，均为1，除非上面出现障碍

        for (int i = 1; i < n; i++)
            obstacleGrid[0][i] = obstacleGrid[0][i] == 1 ? 0 : obstacleGrid[0][i - 1]; //最上一行，均为1，除非左侧出现障碍

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                obstacleGrid[i][j] = obstacleGrid[i][j] == 1 ? 0 : obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
        //每一点，如果原值为1，则结果值为0，否则是左+上的和

        return obstacleGrid[m - 1][n - 1];
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._063UniquePathsII");
    }

    @Test
    public void testSolution() {
        int[][] input = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        Assert.assertEquals(2, uniquePathsWithObstacles(input));
    }
}

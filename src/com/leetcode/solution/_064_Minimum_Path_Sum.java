package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

/**
 * 动态规划，采蘑菇权重问题，相比62，63加入权重
 * <p>
 * Time Complexity: O(M×N). The rectangular grid given to us is of size M×N and we process each cell just once.
 * Space Complexity: O(1). We are utilizing the obstacleGrid as the DP array. Hence, no extra space.
 */

public class _064MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) //null or 0 row or 0 column
            return 0;
        int m = grid.length; //how many rows
        int n = grid[0].length; //how many columns

        for (int i = 1; i < m; i++)
            grid[i][0] += grid[i - 1][0]; //最左一列能采集到的，求和，只有一种采集方法

        for (int i = 1; i < n; i++)
            grid[0][i] += grid[0][i - 1]; //最上一行能采集到的，求和，只有一种采集方法

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                //每一点，是左侧或上方+本点的值，取最小值
                grid[i][j] = Math.min(grid[i - 1][j] + grid[i][j], grid[i][j - 1] + grid[i][j]);

        return grid[m - 1][n - 1];
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._064MinimumPathSum");
    }

    @Test
    public void testSolution() {
        int[][] input = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] input2 = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        Assert.assertEquals(0, minPathSum(input));
        Assert.assertEquals(16, minPathSum(input2));
    }
}

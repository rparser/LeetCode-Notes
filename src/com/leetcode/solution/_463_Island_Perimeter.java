package com.leetcode.solution;

/**
 * 岛屿边长
 * 2loop,先每个格子+4，上或左如果有格子，则各-2
 */

public class _463_Island_Perimeter {
    //O(N), O(1) 岛屿只有在靠近 边界或水 才会 周长 + 1
    public int islandPerimeterDFS(int[][] grid) {
        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid[0].length; c++)
                if (grid[r][c] == 1)
                    // 题目限制只有一个岛屿，计算一个即可
                    return dfs(grid, r, c);

        return 0;
    }
    //已遍历设成2
    private int dfs(int[][] grid, int r, int c) {
        // 如果超出边界 或 为水域 则证明岛屿到了边界，周长+1
        if (!(0 <= r && r < grid.length && 0 <= c && c < grid[0].length)
                || grid[r][c] == 0)
            return 1;
        //如果已遍历，返回0
        if (grid[r][c] == 2)
            return 0;
        //已遍历
        grid[r][c] = 2;
        return dfs(grid, r - 1, c)
                + dfs(grid, r + 1, c)
                + dfs(grid, r, c - 1)
                + dfs(grid, r, c + 1);
    }

    //普通格子周长为4，上面如果有格子或左边有格子，总周长-2
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int result = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 1) {
                    result += 4; //先假设有4个
                    if (i > 0 && grid[i - 1][j] == 1)
                        result -= 2; //上面有格子边长-2
                    if (j > 0 && grid[i][j - 1] == 1)
                        result -= 2; //左边有格子边长-2
                }

        return result;
    }
}

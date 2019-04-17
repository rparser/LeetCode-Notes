package com.leetcode.solution;

/**
 * DFS 解法：把每个为1的点的相邻点全变成0
 *
 */

public class _200NumberofIslands {
    void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') { //超出范围或该点为0
            return;
        }
        grid[r][c] = '0'; //把该点变成0
        dfs(grid, r - 1, c); // 遍历周围四个点
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int num_islands = 0; //岛屿数结果从零计算
        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[0].length; ++c) { //遍历每个点
                if (grid[r][c] == '1') { //如果该点为陆地
                    num_islands++; //岛屿数为0
                    dfs(grid, r, c); //dfs搜索周围的点
                }
            }
        }
        return num_islands;
    }
}

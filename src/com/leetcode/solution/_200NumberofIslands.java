package com.leetcode.solution;

/**
 * 岛屿数目
 * <p>
 * DFS 解法：把每个为1的点的相邻点全变成0
 * Time complexity : O(M×N) where MM is the number of rows and N is the number of columns.
 * Space complexity : worst case O(M×N) in case that the grid map is filled with lands where DFS goes by M×N deep.
 */

public class _200NumberofIslands {
    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') { //该点为0或超出范围，立刻中断返回
            return;
        }
        grid[r][c] = '0'; //把该点变成0
        dfs(grid, r - 1, c); // 遍历周围四个点
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0; //如果空集
        int num_islands = 0; //岛屿数结果从零计算
        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[0].length; ++c) { //遍历每个点
                if (grid[r][c] == '1') { //如果该点为陆地
                    num_islands++; //岛屿数+1
                    dfs(grid, r, c); //dfs搜索周围的点
                }
            }
        }
        return num_islands;
    }
}

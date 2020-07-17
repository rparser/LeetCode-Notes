package com.leetcode.solution;

/**
 * 最大岛屿面积
 * 相对200，cur记录当前岛大小，max记录最大，每到新岛grid[r][c] == 1,cur=0
 */

public class _695_Max_Area_of_Island {
    int curArea = 0;
    int maxArea = 0;
    // O(R * C), O(R∗C)
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1)
            return 0; //如果空

        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid[0].length; c++)
                if (grid[r][c] == 1) { //意味着到了一个新岛，所以curArea重置为0
                    curArea = 0;
                    dfs(grid, r, c);
                }

        return maxArea;
    }

    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0)  //该点为0或超出范围，则返回
            return;

        grid[r][c] = 0;
        curArea++;
        maxArea = Math.max(maxArea, curArea);
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

}

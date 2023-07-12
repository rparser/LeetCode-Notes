package com.leetcode.solution;

/**
 * 最大岛屿面积
 * 相对200，cur记录当前岛大小，max记录最大，每到新岛grid[r][c] == 1则cur=0
 * dfs减枝->grid[r][c] = 0代表访问过
 * O(R*C), O(R*C)
 */

public class _695_Max_Area_of_Island {
    private final int[][] DIRS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    int curArea = 0;
    int maxArea = 0;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1)
            return 0; // 如果空

        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid[0].length; c++)
                if (grid[r][c] == 1) { // 意味着到了一个新岛，所以curArea重置为0
                    curArea = 0;
                    dfs(grid, r, c);
                }
        return maxArea;
    }

    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0)  //该点为0或超出范围，则返回
            return;

        grid[r][c] = 0; // 变成0代表已经访问过
        curArea++;
        maxArea = Math.max(maxArea, curArea);

        for (int[] dir : DIRS) {
            dfs(grid, r + dir[0], c + dir[1]);
        }
    }
}

package com.leetcode.solution;

public class _980_unique_paths_iii {
    public int uniquePathsIII(int[][] grid) {
        int startX = 0, startY = 0, stepNum = 1;  //当grid[i][j] == 2, stepNum++, 这里直接初始化为1
        //遍历获取起始位置和统计总步数
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    startY = i;
                    startX = j;
                    continue;
                }
                if (grid[i][j] == 0) stepNum++;
            }
        }

        return dfs(startX, startY, stepNum, grid);
    }


    public int dfs(int x, int y, int stepSur, int[][] grid) {
        //排除越界的情况和遇到障碍的情况
        if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length || grid[y][x] == -1) return 0;
        if (grid[y][x] == 2) return stepSur == 0 ? 1 : 0;
        grid[y][x] = -1;  //已走过的标记为障碍
        int res = 0;
        res += dfs(x - 1, y, stepSur - 1, grid);
        res += dfs(x + 1, y, stepSur - 1, grid);
        res += dfs(x, y - 1, stepSur - 1, grid);
        res += dfs(x, y + 1, stepSur - 1, grid);
        grid[y][x] = 0;  //dfs遍历完该位置为起始位置的情况后，置零，以不影响后面的dfs
        return res;
    }
}

package com.leetcode.solution;

import java.util.HashSet;
import java.util.Set;

/**
 * 岛屿形状统计
 * 相对于200，增加2set记录1岛屿形状2把1toString加入2，统计2大小
 */

public class _694_NumberofDistinctIslands {
    private void dfs(int[][] grid, int r, int c, int baseX, int baseY, Set<String> set) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) { //该点为0或超出范围，立刻中断返回
            return;
        }
        set.add((r - baseX) + "_" + (c - baseY)); //比200多的：encode变为字符串,记录相对岛屿形状
        grid[r][c] = 0;
        dfs(grid, r + 1, c, baseX, baseY, set); //每个顶点相对位置
        dfs(grid, r - 1, c, baseX, baseY, set);
        dfs(grid, r, c - 1, baseX, baseY, set);
        dfs(grid, r, c + 1, baseX, baseY, set);
    }

    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0; //如果空
        Set<String> result = new HashSet<>(); //比200多的，记录结果
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                Set<String> set = new HashSet<>(); //比200多的，记录形状
                if (grid[r][c] == 1) {
                    dfs(grid, r, c, r, c, set);
                    result.add(set.toString());
                }
            }
        }
        return result.size();
    }
}

package com.leetcode.common;

import java.util.HashSet;
import java.util.Set;

/**
 * dfs函数，先直接非空不超范围return，再递归（方向等）
 * 主函数，验证非空，设置结果集合，多重for遍历，中间if（dfs的条件）在其中dfs
 */

public class DFS模板 {
    private void dfs(int[][] grid, int r, int c, int baseX, int baseY, Set<String> set) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0)  //该点为0或超出范围，立刻中断返回
            return;

        set.add((r - baseX) + "_" + (c - baseY));
        grid[r][c] = 0;
        dfs(grid, r + 1, c, baseX, baseY, set); //每个顶点相对位置
        dfs(grid, r - 1, c, baseX, baseY, set);
        dfs(grid, r, c - 1, baseX, baseY, set);
        dfs(grid, r, c + 1, baseX, baseY, set);
    }

    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0; //如果空则返回
        Set<String> result = new HashSet<>();
        for (int r = 0; r < grid.length; r++) { //多重for遍历
            for (int c = 0; c < grid[0].length; c++) {
                Set<String> set = new HashSet<>();
                if (grid[r][c] == 1) { //dfs条件
                    dfs(grid, r, c, r, c, set);
                    result.add(set.toString());
                }
            }
        }
        return result.size();
    }
}

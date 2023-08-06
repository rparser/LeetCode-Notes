package com.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿数目
 * DFS 解法：把每个为1的点的相邻点全变成0
 * Time complexity : O(M×N) where MM is the number of rows and N is the number of columns.
 * Space complexity : worst case O(M×N) in case that the grid map is filled with lands where DFS goes by M×N deep.
 * 694子问题
 */

public class _200_Number_of_Islands {
    private final int[][] DIRS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    //O(MN), O(MN)
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0; //如果空集
        }
        int count = 0; //岛屿数结果从零计算
        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid[0].length; c++)  //遍历每个点
                if (grid[r][c] == '1') { //如果该点为陆地
                    count++; //岛屿数为0
//                    dfs(grid, r, c); //dfs搜索周围的点
                    bfs(grid, r, c);
                }

        return count;
    }

    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0')  //超出范围或该点为0
            return;
        // 搜索过则变成海 - 变成0
        grid[r][c] = '0';
        // 遍历周围四个点
        for (int[] dir : DIRS) {
            dfs(grid, r + dir[0], c + dir[1]);
        }
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            i = cur[0];
            j = cur[1];
            if (0 <= i && i < grid.length && 0 <= j && j < grid[0].length && grid[i][j] == '1') {
                grid[i][j] = '0';
                for (int[] dir : DIRS) {
                    queue.offer(new int[]{i + dir[0], j + dir[1]});
                }
            }
        }
    }
}

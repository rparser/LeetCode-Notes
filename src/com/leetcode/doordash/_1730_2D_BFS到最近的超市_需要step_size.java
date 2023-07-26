package com.leetcode.doordash;

import java.util.LinkedList;
import java.util.Queue;

public class _1730_2D_BFS到最近的超市_需要step_size {
    private final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int getFood(char[][] grid) {
        int m = grid.length, n = grid[0].length, steps = 0;
        boolean[][] visited = new boolean[m][n]; //记录是否已经访问过
        Queue<int[]> q = new LinkedList<>(); // 储存数组为当前位置

        start:
        // 找到起始点
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == '*') {
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    break start;
                }

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) { // size会变化所以要提前保存
                int[] index = q.poll();
                int curR = index[0], curC = index[1];

                if (grid[curR][curC] == '#')
                    return steps; // 如果找到了#目标，得出结果

                for (int[] direction : directions) { // 原点上下左右四个点加入队列
                    int r = curR + direction[0], c = curC + direction[1]; // 新横坐标和纵坐标
                    if (r >= 0 && c >= 0 && r < m && c < n && grid[r][c] != 'X' && !visited[r][c]) { //如果没找到目标，且此点可通行，且未访问过
                        visited[r][c] = true; // 这个点设成已访问
                        q.add(new int[]{r, c}); // 把这个新的点加入队列
                    }
                }
            }
            steps++; //记录层数
        }
        return -1;
    }
}

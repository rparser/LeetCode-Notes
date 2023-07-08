package com.leetcode.solution;

import java.util.*;

public class _1730_Shortest_Path_to_Get_Food {
    private final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int getFood2(char[][] grid) {
        int n = grid.length, m = grid[0].length, steps = 0;
        boolean[][] visited = new boolean[n][m]; //二维坐标转换为一维坐标，记录是否已经访问过
        Queue<int[]> q = new LinkedList<>();

        start:
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (grid[i][j] == '*') {
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    break start;
                }

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] index = q.poll();
                int curR = index[0], curC = index[1];

                if (grid[curR][curC] == '#')
                    return steps; // 如果找到了#目标，得出结果

                for (int[] direction : directions) { // 原点上下左右四个点加入队列
                    int r = curR + direction[0], c = curC + direction[1]; // 新横坐标和纵坐标
                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] != 'X' && !visited[r][c]) { //如果没找到目标，且此点可通行，且未访问过
                        visited[r][c] = true; // 这个点设成已访问
                        q.add(new int[]{r, c}); // 把这个新的点加入队列
                    }

                }
            }
            steps++;
        }
        return -1;
    }
}

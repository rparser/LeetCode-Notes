package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class _1254_Number_of_Closed_Islands {
    private final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Queue<int[]> queue = new ArrayDeque<>();
                    grid[i][j] = 1;
                    boolean closed = true;

                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] arr = queue.poll();
                        int cx = arr[0], cy = arr[1];
                        if (cx == 0 || cy == 0 || cx == m - 1 || cy == n - 1) {
                            closed = false;
                        }
                        for (int[] dir : DIRS) {
                            int nx = cx + dir[0];
                            int ny = cy + dir[1];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                                grid[nx][ny] = 1;
                                queue.offer(new int[]{nx, ny});
                            }
                        }
                    }
                    if (closed) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
}

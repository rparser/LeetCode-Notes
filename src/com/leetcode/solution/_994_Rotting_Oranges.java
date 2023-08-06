package com.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS好坏番茄，
 * 模板while,for(每层)，取当前值+4个方向，不符合忽略，符合加入队列
 */

public class _994_Rotting_Oranges {
    private final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int freshCount = 0;
        int minute = 0;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j}); // 坏的加入队列（0分钟已经坏了）
                } else if (grid[i][j] == 1) {
                    freshCount++; // 统计好的数量
                }
            }
        }

        if (freshCount == 0) {
            return 0; // 如果没有好的
        }

        //bfs starting from initially rotten oranges
        while (!queue.isEmpty()) {
            minute++;
            int size = queue.size();
            for (int i = 0; i < size; i++) { //BFS 检查每一层
                int[] point = queue.poll(); // 取出坏的
                for (int[] dir : DIRS) { //遍历四个方向
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    // 处理好番茄
                    if (x >= 0 && y >= 0 && x < rows && y < cols && grid[x][y] == 1) {
                        grid[x][y] = 2; // 变坏了
                        queue.offer(new int[]{x, y}); // 把刚变坏的加入队列
                        freshCount--; // 好番茄-1
                    }
                }
            }
        }
        return freshCount == 0 ? minute - 1 : -1; // 如果还剩好番茄则返回-1, minute-1是因为提前加了，最后一分钟没变化
    }
}

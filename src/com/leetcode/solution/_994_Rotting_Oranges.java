package com.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS好坏番茄，
 * 模板while,for(每层)，取当前值+4个方向，不符合忽略，符合加入队列
 */

public class _994_RottingOranges {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count_fresh = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j}); //加入坏队列
                } else if (grid[i][j] == 1) {
                    count_fresh++; //统计好番茄
                }
            }
        }

        if (count_fresh == 0) return 0; //如果没有好的
        int count = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        //bfs starting from initially rotten oranges
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) { //BFS 检查每一层
                int[] point = queue.poll(); //取出rotten
                for (int dir[] : dirs) { //遍历四个方向
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    //超出范围，或空值，或已坏，不处理
                    if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || grid[x][y] == 2) continue;
                    grid[x][y] = 2; //否则变坏
                    queue.offer(new int[]{x, y}); //把坏的加入队列
                    count_fresh--; //好番茄-1
                }
            }
        }
        return count_fresh == 0 ? count - 1 : -1; //如果还剩好番茄则返回-1,count-1是因为最后一步无效
    }
}


package com.leetcode.solution;

import java.util.*;

public class 迷宫 {
    public static boolean maze_path(int[][] maze, int[] start, int[] end) {
        boolean[][] visited = new boolean[5][5];
        Queue<int[]> queue = new LinkedList<>();
        Stack<int[]> path = new Stack<>();

        visited[0][0] = true;

        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        queue.offer(start);

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            path.push(cur);

            for (int i = 0; i < 4; i++) {
                int newX = cur[0] + dir[i][0];
                int newY = cur[1] + dir[i][1];
//                move.prev_x = cur.x;
//                move.prev_y = cur.y;

                if (newX == end[0] && newY == end[1]) {
                    return true;
                }

                if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length && (maze[newX][newY] == 0)
                        && (!visited[newX][newY])) {
                    queue.offer(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }
        return false;
    }
}


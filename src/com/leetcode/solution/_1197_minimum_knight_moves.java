package com.leetcode.solution;

import java.util.*;

class _1197_minimum_knight_moves {
    private static final int[][] DIRECTIONS = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    public int minKnightMoves(int x, int y) {
        //O(MN), O(MN)
        // 遍历日字的方向，每轮round++，一旦达到，就停止
        Queue<int[]> queue = new LinkedList<>();
        //假设从500,500的位置开始走，否则visited会返回负数则出错
        queue.add(new int[]{500, 500});

        int round = 0;
        boolean[][] visited = new boolean[1000][1000];
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            round++;
            int size = queue.size();
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                int[] position = queue.poll();
                int r = position[0];
                int c = position[1];
                if (r == x + 500 && c == y + 500) {
                    // flag弹出
                    flag = true;
                    break;
                }

                for (int[] dir : DIRECTIONS) {
                    int a = r + dir[0];
                    int b = c + dir[1];
                    if (!visited[a][b]) {
                        visited[a][b] = true;
                        queue.add(new int[]{a, b});
                    }
                }
            }
            // flag第二次从while loop弹出
            if (flag)
                break;
        }
        return round - 1;
    }
}
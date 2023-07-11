package com.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS不需要分层记录size,直接根据之前的值+
 */
class _286_Walls_and_Gates {
    // O(mn), O(mn)
    private final int[][] DIRS = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};

    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;
        int m = rooms.length;
        int n = rooms[0].length;

        Queue<int[]> queue = new LinkedList<>();
        // add all gates to the queue
        // add对应offer，remove对应poll，element对应peek，后者不返回异常
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (rooms[i][j] == 0)
                    queue.offer(new int[]{i, j});

        // update distance from gates
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            for (int[] dir : DIRS) {
                int X = curPos[0] + dir[0];
                int Y = curPos[1] + dir[1];
                //跳过的选项
                if (X < 0 || Y < 0 || X >= m || Y >= n || rooms[X][Y] != Integer.MAX_VALUE)
                    continue;

                //更新room值
                rooms[X][Y] = rooms[curPos[0]][curPos[1]] + 1;
                //按顺序推入，所以队列顺序为距离gate距离为0001111222223333333
                queue.offer(new int[]{X, Y});
            }
        }
    }

    public void wallsAndGatesDFS(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++)
            for (int j = 0; j < rooms[0].length; j++)
                if (rooms[i][j] == 0)
                    dfs(rooms, i, j, 0);
    }

    public void dfs(int[][] rooms, int i, int j, int dis) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < dis)
            return;

        rooms[i][j] = dis;
        dfs(rooms, i + 1, j, dis + 1);
        dfs(rooms, i - 1, j, dis + 1);
        dfs(rooms, i, j + 1, dis + 1);
        dfs(rooms, i, j - 1, dis + 1);
    }
}

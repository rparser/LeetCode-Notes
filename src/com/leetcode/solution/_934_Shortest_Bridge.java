package com.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

public class _934_Shortest_Bridge {
    //先DFS把一座岛paint成2，之后BFS 1找2
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int SECOND_ISLAND = 2;
    private static final int VISITED = -1;

    // O(MN),O(MN)
    public int shortestBridge(int[][] grid) {
        //第二个岛已经dfs涂色完毕
        paint2ndBridge(grid);

        int nr = grid.length;
        int nc = grid[0].length;
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < nr; i++)
            for (int j = 0; j < nc; j++)
                if (grid[i][j] == 1) {
                    queue.offer(new Node(i, j, 0));
                    grid[i][j] = VISITED;
                }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int curR = node.r + DIRECTIONS[i][0];
                int curC = node.c + DIRECTIONS[i][1];
                // 超出界限则跳出
                if (curC < 0 || curC >= nc || curR < 0 || curR >= nr || grid[curR][curC] == VISITED)
                    continue;
                //找到则返回，第一个返回的一定是最近距离
                if (grid[curR][curC] == SECOND_ISLAND)
                    return node.step;
                //标注为已访问
                grid[curR][curC] = VISITED;
                //新node推入queue
                queue.offer(new Node(curR, curC, node.step + 1));
            }
        }
        return -1;
    }

    class Node {
        int r;
        int c;
        int step;

        Node(int r, int c, int step) {
            this.r = r;
            this.c = c;
            this.step = step;
        }
    }

    private void paint2ndBridge(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                //找到1开始dfs
                if (A[i][j] == 1) {
                    dfs(A, i, j);
                    return;
                }
            }
        }
    }

    private void dfs(int[][] A, int r, int c) {
        if (r < 0 || r >= A.length || c < 0 || c >= A[0].length || A[r][c] != 1) {
            return;
        }
        // 涂成2
        A[r][c] = SECOND_ISLAND;
        // 继续dfs
        for (int i = 0; i < 4; i++) {
            dfs(A, r + DIRECTIONS[i][0], c + DIRECTIONS[i][1]);
        }
    }
}

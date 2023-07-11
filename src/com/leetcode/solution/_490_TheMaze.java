package com.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 这个题目是Leetcode 505 Maze II的简化版, 这里只需要返回能否访问到就可以。
 * 这里用bfs，从一个节点开始，把4个方向上能够行走到的节点（边界或者1的前面一个）都放到queue里面， 为了避免重复访问，用了一个visited矩阵来保存状态， 因为这里只需要判断能否访问到，所以visited就够了
 * 如果我们是每次循环行走合法的状态的话，然后到循环退出的时候往回退一格的话，可以用比较简洁的代码把4个方向合并到一个循环里面。
 * 我之前写过在循环里面判断break的话，容易出错（上下两个方向要分别判断边界）。
 * <p>
 * 时间复杂度
 * O(MNmax(m,n)), 可能要遍历整个矩阵， 其中对于每个点可能要遍历最大长度
 * <p>
 * 空间复杂度
 * O(M*N), 用了一个visited矩阵和queue, 都会和maze一样大
 * <p>
 * 1. bfs, create a queue, add start
 * 2. try all 4 directions, stop until hit out of border or 1
 * 3. put last valid pos to the queue
 * 4. mark the pos as visited (can NOT change the maze)
 * 5. loop until queue is empty
 * 6. during any queue.poll(), if it's destination, return true
 * 7. if loop is finished, return false
 * <p>
 * visited数组。模板while，当前值到达则返回，for四个方向，当在范围内且可到达while走到底，退一步，如果没visited，加入queue变visited
 */

public class _490_TheMaze {
    public boolean hasPath(int[][] maze, int[] start, int[] dest) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            if (pos[0] == dest[0] && pos[1] == dest[1]) return true;

            for (int k = 0; k < dirs.length; ++k) {
                int x = pos[0] + dirs[k][0];
                int y = pos[1] + dirs[k][1];
                while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                    x += dirs[k][0];
                    y += dirs[k][1];
                }
                x -= dirs[k][0]; //退一步回到可行的位置
                y -= dirs[k][1];
                if (!visited[x][y]) {
                    queue.add(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        return false;
    }
}

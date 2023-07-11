package com.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS蛇和梯子棋盘
 * <p>
 * Time complexity: O(n*n)
 * Space complexity: O(n*n)
 * <p>
 * visited数组。模板while,for(每层：步数)，当前值到达则返回，走过则跳过，标记走过，for六步，next查跳跃值，有跳跃则刷新，加入队列
 * 跳跃值函数int getBoardValue(int[][] board, int num)
 */

public class _909_Snakes_and_Ladders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        boolean[] visited = new boolean[n * n + 1]; //是否已走过
        int move = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int num = queue.poll();
                if (num == n * n) return move; //到达则返回
                if (visited[num]) continue; //走过则跳过
                visited[num] = true; //标记已走过
                for (int j = 1; j <= 6 && num + j <= n * n; j++) { //一共可以走六步
                    int next = num + j;
                    int value = getBoardValue(board, next); //得到跳跃值
                    if (value > 0) next = value; //跳跃值不为-1则到新值
                    if (!visited[next]) queue.offer(next); //如果没走过，加入queue
                }
            }
            move++;
        }
        return -1;
    }

    private int getBoardValue(int[][] board, int num) { //位置对应函数
        int n = board.length;
        int r = (num - 1) / n; //数值的行
        int x = n - 1 - r; //对应的x
        int y = r % 2 == 0 ? num - 1 - r * n : n + r * n - num; //对应的y
        return board[x][y];
    }
}

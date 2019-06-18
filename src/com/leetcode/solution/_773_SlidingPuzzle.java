package com.leetcode.solution;

import java.util.*;

public class _773_SlidingPuzzle {
    public int slidingPuzzle(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                sb.append(board[i][j]);
            }
        }

        String init = sb.toString();
        Queue<String> queue = new LinkedList<>();
        queue.add(init);
        Set<String> visited = new HashSet<>();
        visited.add(init);
        /*
        index map
        [0,1,2],
        [3,4,5]
        */
        int[][] dirs = {
                {1, 3},  // 0 can to 1 and 3
                {0, 2, 4},
                {1, 5},
                {0, 4},
                {1, 3, 5},
                {2, 4}
        };

        int steps = 0;
        while (!queue.isEmpty()) {
            Queue<String> nextQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                String cur = queue.poll();
                if ("123450".equals(cur)) {
                    return steps;
                }
                int zeroIndex = cur.indexOf('0');
                for (int nextIndex : dirs[zeroIndex]) {
                    char[] arr = cur.toCharArray();
                    arr[zeroIndex] = arr[nextIndex];
                    arr[nextIndex] = '0';
                    String next = new String(arr);
                    if (!visited.contains(next)) {
                        nextQueue.add(next);
                        visited.add(next);
                    }
                }
            }
            queue = nextQueue;
            steps++;
        }
        return -1;
    }
}

package com.leetcode.solution;

import java.util.*;

public class _773_SlidingPuzzle {
    public int slidingPuzzle(int[][] board) {
        int kRows = board.length;
        int kCols = board[0].length;
        StringBuilder startSb = new StringBuilder();
        StringBuilder goalSb = new StringBuilder();
        int num = 0;
        for (int i = 0; i < kRows; ++i)
            for (int j = 0; j < kCols; ++j) {
                startSb.append(board[i][j]);
                goalSb.append(num);
                num++;
            }

        String start = startSb.toString();
        String goal = goalSb.toString();
        goal = "123450";
        if (start.equals(goal)) return 0;

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; //four directions

        Set<String> visited = new HashSet<>();
        visited.add(start);

        int steps = 0;
        Queue<String> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            ++steps;
            int size = q.size();
            while (size-- > 0) {
                String s = q.poll();
                int p = s.indexOf('0');
                int y = p / kCols;
                int x = p % kCols;
                for (int i = 0; i < 4; ++i) {
                    int tx = x + dirs[i][0];
                    int ty = y + dirs[i][1];
                    if (tx < 0 || ty < 0 || tx >= kCols || ty >= kRows) continue;
                    int pp = ty * kCols + tx;
                    String t = s;
                    char ch[] = t.toCharArray();
                    char temp = ch[p];
                    ch[p] = ch[pp];
                    ch[pp] = temp;
                    t = new String(ch);
                    if (visited.contains(t)) continue;
                    if (t.equals(goal)) return steps;
                    visited.add(t);
                    q.add(t);
                }
            }
        }
        return -1;
    }
}

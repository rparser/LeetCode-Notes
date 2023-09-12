package com.leetcode.solution;

public class _2038_Remove_Colored_Pieces_if_Both_Neighbors_are_the_Same_Color {
    public boolean winnerOfGame(String colors) {
        char[] cs = colors.toCharArray();
        int n = cs.length;
        int a = 0, b = 0;
        for (int i = 1; i < n - 1; i++) {
            if (cs[i] == 'A' && cs[i - 1] == 'A' && cs[i + 1] == 'A') {
                a++;
            }
            if (cs[i] == 'B' && cs[i - 1] == 'B' && cs[i + 1] == 'B') {
                b++;
            }
        }
        return a > b;
    }
}

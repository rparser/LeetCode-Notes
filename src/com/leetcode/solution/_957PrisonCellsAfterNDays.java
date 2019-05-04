package com.leetcode.solution;

import java.util.*;

/**
 * 监狱开灯
 * <p>
 * Time complexity: O(2^N) (不一定有loop)
 * Space complexity: O(2^N)
 */

public class _957PrisonCellsAfterNDays {
    public int[] prisonAfterNDays(int[] cells, int N) {
        if (cells == null || cells.length == 0 || N <= 0) return cells;
        boolean hasLoop = false;
        int cycle = 0; //cycle计数，计算多少个才有loop
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int[] next = nextDay(cells);
            String key = Arrays.toString(next);
            if (!set.contains(key)) { //如果set里没有，则加入set
                set.add(key);
                cycle++;
            } else { //此时有loop
                hasLoop = true;
                break; //直接break重新计算
            }
            cells = next;
        }
        if (hasLoop) { // 如果有loop执行n次nextday
            N %= cycle;
            for (int i = 0; i < N; i++) {
                cells = nextDay(cells);
            }
        }
        return cells;
    }

    private int[] nextDay(int[] cells) {
        int[] tmp = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++) {
            tmp[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return tmp;
    }
}

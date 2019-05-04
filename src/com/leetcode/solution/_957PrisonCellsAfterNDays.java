package com.leetcode.solution;

import java.util.*;

/**
 * 监狱开灯
 */

public class _957PrisonCellsAfterNDays {
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<Integer, int[]> map = new HashMap<>();
        boolean isLooping = false;
        int headKey = 0;
        for (int i = 1; i <= N; i++) {
            int currKey = 0;
            for (int k = 0; k < 8; k++)
                currKey = currKey * 2 + cells[k];
            if (map.containsKey(currKey)) {
                if (!isLooping) { // 还不存在loop
                    isLooping = true;
                    headKey = currKey;
                    N = N - i;
                    i = 0;
                } else if (currKey == headKey) {
                    N = (N - i) % (i);
                    i = 0;
                }
                cells = map.get(currKey);
            } else {
                int[] nextCells = new int[cells.length];
                for (int j = 1; j < 7; j++) {
                    if (cells[j - 1] == cells[j + 1]) nextCells[j] = 1;
                }
                map.put(currKey, nextCells);
                cells = nextCells;
            }
        }
        return cells;
    }
}

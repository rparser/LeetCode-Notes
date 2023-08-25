package com.leetcode.solution;

import java.util.HashMap;
import java.util.Map;

public class _2244_Minimum_Rounds_to_Complete_All_Tasks {
    public int minimumRounds(int[] tasks) {
        int result = 0;
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int task : tasks) {
            cntMap.put(task, cntMap.getOrDefault(task, 0) + 1);
        }
        for (int val : cntMap.values()) {
            if (val == 1) {
                return -1;
            }
            if (val % 3 == 0) {
                result += val / 3;
            } else {
                result += val / 3 + 1;
            }
        }
        return result;
    }
}

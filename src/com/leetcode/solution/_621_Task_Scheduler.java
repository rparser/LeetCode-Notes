package com.leetcode.solution;

import java.util.*;

class _621_Task_Scheduler {
    public int leastInterval(char[] tasks, int n) {
        // <任务，统计>
        Map<Character, Integer> map = new HashMap<>();
        // 做成表格，每行宽度为(n+1)，长度为最频繁的字母数
        // count是最后一行有多少并列,也就是统计有多少并列最多
        int max = 0, count = 0;

        for (char c : tasks) {
            // 统计个数
            map.put(c, map.getOrDefault(c, 0) + 1);
            // 找到最多，且有多少并列最多
            if (map.get(c) > max) {
                max = map.get(c);
                count = 0;
            }
            if (map.get(c) == max)
                count++;
        }
        //-1是除去最后一行
        int v1 = (n + 1) * (max - 1) + count;
        int v2 = tasks.length;
        return Math.max(v1, v2);
    }
}
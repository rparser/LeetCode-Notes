package com.leetcode.doordash;

import java.util.HashMap;
import java.util.Map;

/**
 * 做成表格，每行宽度为(n+1) - 因为n是间隔，长度为最频繁的字母数
 * 比如(A,A,A,B,B,B,C,D) - n=2, 所以每行为3
 * ABC
 * ABD
 * AB*
 * AB/ - 此时除去最后一位为全满，*是空闲
 * 此时maxChar=4, maxCount=2
 * O(M), O(1)
 */

class _621_LI_任务有间隔_完成ABCD {
    public int leastInterval(char[] tasks, int n) {
        // <任务，统计>
        Map<Character, Integer> map = new HashMap<>();
        // 做成表格，每行宽度为(n+1) - 因为n是间隔，长度为最频繁的字母数
        // maxCount是最后一行有多少并列,也就是统计有多少并列最多
        int maxChar = 0, maxCount = 0;

        for (char c : tasks) {
            // 统计个数
            map.put(c, map.getOrDefault(c, 0) + 1);
            // 找到最多，且有多少并列最多
            if (map.get(c) > maxChar) {
                maxChar = map.get(c);
                maxCount = 0;
            }
            if (map.get(c) == maxChar)
                maxCount++;
        }
        // -1是除去最后一行
        int v1 = (n + 1) * (maxChar - 1) + maxCount; // maxCount是最后一行有几个
        int v2 = tasks.length;
        return Math.max(v1, v2);
    }
}
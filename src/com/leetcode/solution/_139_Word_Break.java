package com.leetcode.solution;

import java.util.*;

/**
 * 思路：Sub-problem is to check if s[j...i） 是否在dict中，double for loop to track,
 * eg:leetcode dp[0(j)]&&contains key s[0-3(i=4)], set dp[4] = true
 * dp[4(j)]&& contains key s[4-7(i=8)], return dp[8(n)]
 * 关键字：DP,substring[start,end)
 * Complexity：O(N^2) for DP
 */

public class _139_Word_Break {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict); // 放到set search变O(1)
        Queue<Integer> queue = new LinkedList<>(); // 队列里是index，代表已经处理到这个地方了
        boolean[] visited = new boolean[s.length()];
        queue.add(0);

        while (!queue.isEmpty()) {
            int start = queue.poll();
            if (!visited[start]) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end); // 出现这个单词则把index加入队列
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = true;
            }
        }
        return false;
    }

    public boolean wordBreakDP(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1]; //dp[i+1] means the current s[0...i] check
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {//s[j,i)
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}

package com.leetcode.solution;

import java.util.*;

/**
 * 思路：Sub-problem is to check if s[j...i） 是否在dict中，double for loop to track,
 * eg:leetcode dp[0(j)]&&contains key s[0-3(i=4)], set dp[4] = true
 *                     dp[4(j)]&& contains key s[4-7(i=8)], return dp[8(n)]
 * 关键字：DP,substring[start,end)
 * Complexity：O(N^2) for DP
 */

public class _139_WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];//dp[i+1] means the current s[0...i] check
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

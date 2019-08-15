package com.leetcode.solution;

import java.util.*;

/**
 *  O(N+AlogA).
 */

public class _767_ReorganizeString {
    public String reorganizeString(String S) {
        int[] count = new int[26];
        int maxCount = 0;
        int sum = S.length();
        for (char c : S.toCharArray()) {
            count[c - 'a'] += 100;
            if (count[c - 'a'] > maxCount) maxCount = count[c - 'a'];
        }
        if (maxCount > (sum * 100 + 100) / 2) return "";
        for (int i = 0; i < 26; i++) count[i] += i;
        Arrays.sort(count);
        char[] ans = new char[sum];
        int t = 1;
        for (int code : count) {
            int ct = code / 100;
            char ch = (char) ('a' + (code % 100));
            if (ct > (sum + 1) / 2) return "";
            for (int i = 0; i < ct; ++i) {
                if (t >= sum) t = 0;
                ans[t] = ch;
                t += 2;
            }
        }
        return String.valueOf(ans);
    }
}

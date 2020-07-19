package com.leetcode.solution;

/**
 * Try all possible i and find the longest palindromic string whose center is i (odd case) and i / i + 1 (even case).
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 * 最长回文子串
 */

public class _005_Longest_Palindromic_Substring {
    // 中心扩展O(n^2), O(1)
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) { //以每个字母作为中心点
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) { //end, start为之前存储的最大起点终点
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) { //如果一样各移动一位
            L--;
            R++;
        }
        return R - L - 1; //返回长度
    }

    /**
     * 暴力解法 O(N^2), O(N)
     */

    public String longestPalindrome7(String s) {
        int n = s.length();
        String result = "";
        boolean[] P = new boolean[n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= i; j--) {
                P[j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || P[j - 1]);
                if (P[j] && j - i + 1 > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }
        return result;
    }
}

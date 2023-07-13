package com.leetcode.solution;

class _409_Longest_Palindrome {
    public int longestPalindrome(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray())
            count[c]++;

        int result = 0;
        boolean flag = false;
        for (int v : count) { //只有一个奇数可以使用
            result += v / 2 * 2; //奇数也变为一半
            if (!flag && v % 2 == 1)
                flag = true;
        }
        if (flag) result++;
        return result;
    }
}

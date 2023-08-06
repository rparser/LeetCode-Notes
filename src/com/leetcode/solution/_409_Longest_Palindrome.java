package com.leetcode.solution;

class _409_Longest_Palindrome {
    public int longestPalindrome(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        int res = 0;
        // 判断是否有奇数
        boolean flag = false;
        for (int a : count) {
            if (a % 2 == 1) {
                flag = true;
                // 有的话只能用到c-1;
                res += (a - 1);
            } else {
                res += a;
            }
        }
        return flag ? res + 1 : res;
    }
}

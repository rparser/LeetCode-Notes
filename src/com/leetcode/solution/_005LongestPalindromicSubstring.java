package com.leetcode.solution;

/**
 * 最长回文子串
 */

public class _005LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length == 0) return "";
        String reverse = new StringBuffer(s).reverse().toString();

        int[][] arr = new int[length][length]; // 存储j行最长长度
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++) {
                if (s.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[i][j] = 1; // 当 i = 0 或者 j = 0 的时候单独分析，字符相等的话 arr [ i ][ j ] 就赋为 1 。
                    } else {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }
                }
                if (arr[i][j] > maxLen) {
                    int beforeRev = length - 1 - j;
                    if (beforeRev + arr[i][j] - 1 == i) { //判断下标是否对应
                        maxLen = arr[i][j];
                        maxEnd = i;
                    }
                }
            }


        /**
         * 一维数组解法
         */
        int[] arr2 = new int[length]; // 存储j行最长长度
        int maxLen2 = 0;
        int maxEnd2 = 0;
        for (int i = 0; i < length; i++)
            for (int j = length - 1; j >= 0; j--) {
                if (s.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr2[j] = 1; // 当 i = 0 或者 j = 0 的时候单独分析，字符相等的话 arr [ i ][ j ] 就赋为 1 。
                    } else {
                        arr2[j] = arr2[j - 1] + 1;
                    }
                    //之前二维数组，每次用的是不同的列，所以不用置 0 。
                } else {
                    arr2[j] = 0;
                }
                if (arr2[j] > maxLen) {
                    int beforeRev = length - 1 - j;
                    if (beforeRev + arr2[j] - 1 == i) { // 判断下标是否对应
                        maxLen2 = arr2[j];
                        maxEnd2 = i;
                    }

                }
            }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
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

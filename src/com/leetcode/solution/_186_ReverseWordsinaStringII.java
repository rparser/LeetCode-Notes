package com.leetcode.solution;

/**
 * String是immutable，所以需要用char array来做。
 * 要求是否可以不需要额外空间，即只在原来的char数组上操作。
 * 首先可以把整个char数组反转，然后再对每个单词进行反转。
 * <p>
 * inspace操作
 */

public class _186_ReverseWordsinaStringII {
    public void reverseWords(char[] s) {
        reverseWords(s, 0, s.length - 1);
        for (int i = 0, j = 0; i <= s.length; i++) {
            if (i == s.length || s[i] == ' ') {
                reverseWords(s, j, i - 1);
                j = i + 1;
            }
        }
    }

    private void reverseWords(char[] s, int begin, int end) {
        while (begin < end) {
            char c = s[begin];
            s[begin] = s[end];
            s[end] = c;
            begin++;
            end--;
        }
    }

    private void reverseString(String str) {
        str = new StringBuilder(str).reverse().toString();
    }
}

package com.leetcode.solution;

class _344_Reverse_String {
    public void reverseString(char[] s) {
        int length = (s.length - 1);

        for (int i = 0; i < (s.length) / 2; i++) {
            char temp = s[i];
            s[i] = s[length - i];
            s[length - i] = temp;
        }
    }
}

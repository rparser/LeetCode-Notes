package com.leetcode.solution;

/**
 * 实现indexOf()
 */

public class _028_Implement_strStr {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        } // edge case: "",""=>0  "a",""=>0
        for (int i = 0; i <= haystack.length() - needle.length(); i++) { // 直到找到两个长度差值
            for (int j = 0; j < needle.length() && haystack.charAt(i + j) == needle.charAt(j); j++) { //必须找到相同的字母才开始这个for
                if (j == needle.length() - 1) {
                    return i;
                }
            }
        } //如果已经到了最后一个

        return -1;
    }

    //    时间复杂度是O(n+k)
    public int strStrKMP(String haystack, String needle) {
        int strLen = haystack.length(), subLen = needle.length();
        if (subLen == 0) {
            return 0;
        }
        if (strLen == 0) {
            return -1;
        }
        // 构建状态机
        int[][] FSM = new int[subLen][256];
        int X = 0, match = 0;
        for (int i = 0; i < subLen; i++) {
            match = (int) needle.charAt(i);
            for (int j = 0; j < 256; j++)
                // 当前状态 + 匹配失败字符 = 孪生词缀状态 + 匹配字符
                FSM[i][j] = FSM[X][j];


            FSM[i][match] = i + 1;
            if (i > 1) {
                // 下一孪生前缀状态 = X + match
                X = FSM[X][match];
            }
        }
        // 匹配子串
        int state = 0;
        for (int i = 0; i < strLen; i++) {
            state = FSM[state][haystack.charAt(i)];
            if (state == subLen)
                return i - subLen + 1;
        }
        return -1;
    }
}

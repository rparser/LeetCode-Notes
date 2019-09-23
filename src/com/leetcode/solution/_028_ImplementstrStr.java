package com.leetcode.solution;

/**
 * 实现indexOf()
 */

public class _028_ImplementstrStr {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0; // edge case: "",""=>0  "a",""=>0
        for (int i = 0; i <= haystack.length() - needle.length(); i++) { // 直到找到两个长度差值
            for (int j = 0; j < needle.length() && haystack.charAt(i + j) == needle.charAt(j); j++) //必须找到相同的字母才开始这个for
                if (j == needle.length() - 1) return i; //如果已经到了最后一个
        }
        return -1;
    }
}

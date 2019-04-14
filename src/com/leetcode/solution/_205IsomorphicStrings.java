package com.leetcode.solution;

/**
 * 1. 设置两个char[256]数组，数组1的位置值和数组2的实际值对应，反之亦然   sMapping[sChar] = tChar;  tMapping[tChar] = sChar;
 * 2. 依次比较字母，如果不存在相应值则设置1，否则看否一致，不一致则return false
 * 2. 最后return true
 */

public class _205IsomorphicStrings {
    public boolean isIsomorphic(String sString, String tString) {
        if (sString.length() != tString.length()) return false;
        char[] s = sString.toCharArray();
        char[] t = tString.toCharArray();
        char[] sMapping = new char[256];
        char[] tMapping = new char[256];
        for (int i = 0; i < sString.length(); i++) {
            char sChar = s[i];
            char tChar = t[i];
            if (sMapping[sChar] == '\u0000' && tMapping[tChar] == '\u0000') { //'\u0000' 是char默认值
                sMapping[sChar] = tChar; //设置对应关系
                tMapping[tChar] = sChar;
            } else {
                if (sMapping[sChar] != tChar || tMapping[tChar] != sChar) //对应出现不一致
                    return false;
            }
        }
        return true;
    }
}

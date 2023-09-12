package com.leetcode.solution;

public class _2068_Check_Whether_Two_Strings_are_Almost_Equivalent {
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] res = new int[26];
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        for (int i = 0; i < word1.length(); i++) {
            res[w1[i] - 'a']++;
            res[w2[i] - 'a']--;
        }
        boolean flag = true;
        for (int i : res) {
            if (i > 3 || i < -3) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}

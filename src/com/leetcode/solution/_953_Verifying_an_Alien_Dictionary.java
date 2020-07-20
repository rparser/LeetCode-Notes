package com.leetcode.solution;

/**
 * Time Complexity: O(C), where C is the total content of words.
 * Space Complexity: O(1)
 */

public class _953_Verifying_an_Alien_Dictionary {
    private int[] ranking = new int[26];

    public boolean isAlienSorted(String[] words, String order) {
        //得出每个字母的排名
        for (int i = 0; i < order.length(); i++)
            ranking[order.charAt(i) - 'a'] = i;

        for (int i = 1; i < words.length; i++)
            if (compare(words[i - 1], words[i]) > 0)
                return false;

        return true;
    }

    private int compare(String s1, String s2) {
        int n = s1.length(), m = s2.length(), compared = 0;
        for (int i = 0, j = 0; i < n && j < m && compared == 0; i++, j++)
            // 如果compared不为0，即跳出for loop返回比较过的值
            compared = ranking[s1.charAt(i) - 'a'] - ranking[s2.charAt(j) - 'a'];

        // 如果compared==0，未分出胜负，则返回长度，否则返回比较过的值
        return compared == 0 ? n - m : compared;
    }
}

package com.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 思路： sliding window套路 using 2 pointer, key is to check 'end - start + 1 == t.length()' to find valid anagram.
 * Complexity: O(N) ~ O(1)
 */

public class _438_Find_All_Anagrams_in_a_String {
    // O(N), O(1), p是短字符串
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return result;
        }
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        // 记录前pLen个字母
        for (int i = 0; i < pLen; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }
        // 如果一开始就满足了
        if (Arrays.equals(sCount, pCount)) {
            result.add(0);
        }
        // 处理每一个s字符串的字母
        for (int i = pLen; i < sLen; i++) {
            // 加新的去旧的
            sCount[s.charAt(i - pLen) - 'a']--;
            sCount[s.charAt(i) - 'a']++;
            if (Arrays.equals(sCount, pCount)) {
                result.add(i - pLen + 1);
            }
        }
        return result;
    }
}

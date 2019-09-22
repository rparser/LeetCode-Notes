package com.leetcode.solution;

import java.util.*;

/**
 * Time complexity : O(N) in the best case of k distinct characters in the string
 * and O(Nk) in the worst case of N distinct characters in the string.
 * Space complexity : O(k) since additional space is used only for a hashmap with at most k + 1 elements.
 */

public class _340_LongestSubstringwithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if (n * k == 0) return 0;
        int left = 0, right = 0; // sliding window left and right pointers
        int max_len = 1;
        HashMap<Character, Integer> map = new HashMap<>(); //hashmap character -> its rightmost position in the sliding window

        while (right < n) {
            map.put(s.charAt(right), right++); // add new character and move right pointer

            if (map.size() == k + 1) { // slidewindow contains 3 characters
                int del_idx = Collections.min(map.values()); // delete the leftmost character,注意Collections.min
                map.remove(s.charAt(del_idx));
                left = del_idx + 1; // move left pointer of the slidewindow
            }
            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }
}

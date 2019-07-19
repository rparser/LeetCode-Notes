package com.leetcode.solution;

import java.util.*;

/**
 * 思路： string window套路 using 2 pointer, key is to check 'end - start + 1 == t.length()' to find valid anagram.
 * Complexity: O(N) time - loop, O(N) space - map
 */

public class _438_FindAllAnagramsinaString {
    public List<Integer> findAnagrams(String s, String t) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();//<all char, freq in t>
        for (char c : s.toCharArray()) map.put(c, 0);
        for (char c : t.toCharArray()) {
            if (map.containsKey(c)) map.put(c, map.get(c) + 1);
            else return res;
        }
        int start = 0, end = 0;
        int counter = t.length();
        while (end < s.length()) {
            char cur = s.charAt(end);
            if (map.get(cur) > 0) counter--;
            map.put(cur, map.get(cur) - 1);
            while (counter == 0) {
                if (end - start + 1 == t.length()) res.add(start);
                char c2 = s.charAt(start);
                map.put(c2, map.get(c2) + 1);
                if (map.get(c2) > 0) counter++;
                start++;
            }
            end++;
        }
        return res;
    }
}

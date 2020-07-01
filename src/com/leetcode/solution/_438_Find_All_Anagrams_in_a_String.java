package com.leetcode.solution;

import java.util.*;

/**
 * 思路： sliding window套路 using 2 pointer, key is to check 'end - start + 1 == t.length()' to find valid anagram.
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
        int left = 0, right = 0;
        int counter = t.length();
        while (right < s.length()) {
            char cur = s.charAt(right);
            if (map.get(cur) > 0)
                counter--;

            map.put(cur, map.get(cur) - 1);

            while (counter == 0) {
                if (right - left + 1 == t.length())
                    res.add(left);

                char c2 = s.charAt(left);
                map.put(c2, map.get(c2) + 1);
                if (map.get(c2) > 0)
                    counter++;
                left++;
            }
            right++;
        }
        return res;
    }
}

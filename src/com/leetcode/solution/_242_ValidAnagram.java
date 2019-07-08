package com.leetcode.solution;

/**
 * Time complexity : O(n). Time complexity is O(n) because accessing the counter table is a constant time operation.
 * Space complexity : O(1). Although we do use extra space, the space complexity is O(1) because the table's size stays constant no matter how large nn is.
 */

public class _242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter)
            if (count != 0) return false;

        return true;
    }
}

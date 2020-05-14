package com.leetcode.solution;

import java.util.*;

/**
 * 最长子串没有重复字符
 * <p>
 * Time complexity : O(n). Index j will iterate n times.
 * Space complexity (HashMap) : O(min(m,n)).
 * We need O(k) space for checking a substring has no duplicate characters, where k is the size of the Set.
 * The size of the Set is upper bounded by the size of the string n and the size of the charset/alphabet m.
 */

public class _003_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), result = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        for (int j = 0, i = 0; j < n; j++) {
            // 如果新加入的j字母已经有了, i有可能要前移（移动到当前值和存储值的max,必须是max因为i不能后退（如果只是存储值可能会后退））
            if (map.containsKey(s.charAt(j))) i = Math.max(map.get(s.charAt(j)), i);
            result = Math.max(result, j - i + 1); // 更新result
            map.put(s.charAt(j), j + 1); // 加入当前字母对应的最后位置+1
        }
        return result;
    }
}
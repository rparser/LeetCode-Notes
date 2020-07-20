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

public class _003_Longest_Substring_Without_Repeating_Characters {
    //O(N),O(N)
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), result = 0;

        Map<Character, Integer> map = new HashMap<>(); // current index of character
        for (int left = 0, right = 0; right < n; right++) {
            // 如果新加入的right字母已经有了, left有可能要前移（移动到当前值和存储值的max,必须是max因为i不能后退（如果只是存储值可能会后退））
            // 比如例子"abba", 到第二个a时，left不能后退必须为2
            if (map.containsKey(s.charAt(right)))
                left = Math.max(map.get(s.charAt(right)), left);

            result = Math.max(result, right - left + 1); // 更新result
            map.put(s.charAt(right), right + 1); // +1是为了排除这个字母
        }
        return result;
    }
}

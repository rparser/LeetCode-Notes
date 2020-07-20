package com.leetcode.solution;

import java.util.*;

/**
 * 思路： sliding window套路 using 2 pointer, key is to check 'end - start + 1 == t.length()' to find valid anagram.
 * Complexity: O(N) time - loop, O(N) space - map
 */

public class _438_Find_All_Anagrams_in_a_String {
    // O(N), O(N), t是短字符串
    public List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new ArrayList<>();
        // t的<char, 频率>
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, 0);
        // 统计t分别需要多少字母
        for (char c : t.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                return result;
        }

        int left = 0, right = 0;
        //一共需要满足的字母数
        int counter = t.length();

        while (right < s.length()) {
            char cRight = s.charAt(right);
            // 如果t需要这个字母（当前map.get(cur) > 0）
            if (map.get(cRight) > 0)
                counter--;
            // 少需要一个（value可能为负数），因为存了很多
            map.put(cRight, map.get(cRight) - 1);
            // 当为0时，即所有 t 需要的字母都满足,此时left需要走到right
            while (counter == 0) {
                // 长度必须一致
                if (right - left + 1 == t.length())
                    result.add(left);
                //即将退出的字母（left即将++）
                char cLeft = s.charAt(left);
                map.put(cLeft, map.get(cLeft) + 1);
                // 当需要的value > 0，则代表多需要要给字母
                if (map.get(cLeft) > 0)
                    counter++;

                left++;
            }
            right++;
        }
        return result;
    }
}

package com.leetcode.solution;

import java.util.*;

/**
 * Time complexity : O(N) in the best case of k distinct characters in the string
 * and O(Nk) in the worst case of N distinct characters in the string.
 * Space complexity : O(k) since additional space is used only for a hashmap with at most k + 1 elements.
 */

public class _340_Longest_Substring_with_At_Most_K_Distinct_Characters {
    /**
     * 为了达到 O(N) 的效率，我们可以使用LinkedHashMap，保证以下四种操作都可以在 O(1) 时间完成：HashMap不能保证返回最先插入对
     * 插入键
     * 获取键 / 检查键是否存在
     * 删除键
     * 返回最先 / 最后插入的键值对
     **/
    // O(N) LinkedHashMap
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if (n * k == 0) return 0;

        int left = 0;
        int right = 0;
        // 字符和它最右的index
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>(k + 1);
        int max_len = 1;

        while (right < n) {
            Character c = s.charAt(right);
            // 必须先删除，这样c就是最后加入的了（否则c可能在中间更新）
            map.remove(c);
            map.put(c, right);
            right++;

            // slide window contains k + 1 characters
            if (map.size() > k) {
                // 找到map里第一个entry
                Map.Entry<Character, Integer> leftmost = map.entrySet().iterator().next();
                map.remove(map.entrySet().iterator().next().getKey());
                // 删除了之前最左的，所以此时窗口左指针+1
                left = leftmost.getValue() + 1;
            }
            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }

    //O(NK)解法
    public int lengthOfLongestSubstringKDistinctHashMap(String s, int k) {
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

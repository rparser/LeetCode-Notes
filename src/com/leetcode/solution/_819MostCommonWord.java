package com.leetcode.solution;

import java.util.*;

/**
 * 最常见单词
 * 最常见的K个词见692
 *
 * Time Complexity: O(P+B), where P is the size of paragraph and B is the size of banned.
 * Space Complexity: O(P+B), to store the count and the banned set.
 */

public class _819MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> ban = new HashSet<>(Arrays.asList(banned)); // 设置屏蔽词的set
        Map<String, Integer> count = new HashMap<>(); // 单词统计map
        String[] words = paragraph.replaceAll("[^a-zA-Z]+", " ").toLowerCase().split("\\s+");
        // "\\W+" : 所有不是字母数字下划线的字符， "\\s+" ： 所有空白字符
        for (String w : words) if (!ban.contains(w)) count.put(w, count.getOrDefault(w, 0) + 1); // 不在ban列表则加入HashMap
        return Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey(); //HashMap取结果，按value排列
        //Map.Entry.comparingByValue() 固定用法
    }
}

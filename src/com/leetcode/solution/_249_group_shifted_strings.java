package com.leetcode.solution;

import java.util.*;

/**
 * 整体思路：给每个字符做个唯一编码映射为 map 的 key
 * <p>
 * 字符串转字符数组，相邻字符数组差值如果一致的话表示可移位成功。
 */
class _249_group_shifted_strings {
    //O(N), O(N) 序列化字符串
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strings) {
            StringBuilder sb = new StringBuilder();

            for (char c : str.toCharArray()) {
                sb.append("#");
                // +26是为了变为正数
                int shift = (c - str.charAt(0) + 26) % 26;
                sb.append(shift);
            }

            String key = sb.toString();
            if (!map.containsKey(key))
                map.put(key, new ArrayList<>());

            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
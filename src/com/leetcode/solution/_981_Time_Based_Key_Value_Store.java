package com.leetcode.solution;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class _981_Time_Based_Key_Value_Store {
    Map<String, TreeMap<Integer, String>> M;

    public _981_Time_Based_Key_Value_Store() {
        M = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!M.containsKey(key))
            M.put(key, new TreeMap<>());

        M.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!M.containsKey(key)) return "";
//使用 TreeMap.floorKey(timestamp) 来找到小于等于给定时间戳 timestamp 的最大时间戳。
        TreeMap<Integer, String> tree = M.get(key);
        Integer t = tree.floorKey(timestamp);
        return t != null ? tree.get(t) : "";
    }
}

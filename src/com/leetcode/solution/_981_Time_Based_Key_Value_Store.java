package com.leetcode.solution;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class _981_Time_Based_Key_Value_Store {
    Map<String, TreeMap<Integer, String>> map;

    public _981_Time_Based_Key_Value_Store() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new TreeMap<>());
        }
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        //使用 TreeMap.floorKey(timestamp) 来找到小于等于给定时间戳 timestamp 的最大时间戳。
        TreeMap<Integer, String> tree = map.get(key);
        Integer lower = tree.floorKey(timestamp);
        return lower != null ? tree.get(lower) : "";
    }
}

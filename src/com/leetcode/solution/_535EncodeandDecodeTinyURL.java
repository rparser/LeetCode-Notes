package com.leetcode.solution;

import java.util.*;

/**
 * 思路：1. 设置两个map long->short, short->long
 * 2. decode先验证long->short是否存在，否则直接返回short
 * 3. 不存在则 (随机数*数组长度) 选去字符数组中的某一个，生成长度6的key（6可以改变）
 * 4. 验证此key是否已存在，不存在则保存在两个mapl里，存在则重来3
 */

public class _535EncodeandDecodeTinyURL {
    Map<String, String> index = new HashMap<>(); //正向定义
    Map<String, String> revIndex = new HashMap<>(); //反向先查找是否有已存在的，否则直接返回已存的对应shortUrl
    static String BASE_HOST = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (revIndex.containsKey(longUrl)) return BASE_HOST + revIndex.get(longUrl);
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String key = null;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                int r = (int) (Math.random() * charSet.length());
                sb.append(charSet.charAt(r));
            }
            key = sb.toString();
        } while (index.containsKey(key)); //查找是否已有key,如果已有则换下一个
        index.put(key, longUrl);
        revIndex.put(longUrl, key); //两个map都添加，一个用来查找longUrl，一个用来验证longUrl是否已存在
        return BASE_HOST + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return index.get(shortUrl.replace(BASE_HOST, ""));
    }
}

package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.*;

/**
 * 归类易位构词anagram字符串
 * <p>
 * 利用质数积
 * 每个map.key显示有多少个什么字母组成这个字符串，map.value是0，1，2对应List<List<String>>里不同的位置
 * Time Complexity: O(NK), where NN is the length of strs, and KK is the maximum length of a string in strs.
 * Counting each string is linear in the size of the string, and we count every string.
 * Space Complexity: O(NK), the total information content stored in ans.
 **/

public class _049_Group_Anagrams {
    // O(NK), O(NK)
    public List<List<String>> groupAnagramsRegular(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> result = new HashMap<>(); // key是"排列结构",value是符合当前结构的List
        //计数数组
        int[] count = new int[26];
        for (String s : strs) {
            //清零计数数组
            Arrays.fill(count, 0);
            for (char c : s.toCharArray())
                count[c - 'a']++;
            // 组成“#1#2#3#0#0#0...#0”代表每个字母各有几个
            //序列化Serialize
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }

            String key = sb.toString();
            if (!result.containsKey(key))
                result.put(key, new ArrayList<>());

            result.get(key).add(s); //把此字符串s加入HashMap
        }
        return new ArrayList<>(result.values());
    }

    //质数积做法xul
    public static List<List<String>> groupAnagrams(String[] strs) {
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
                41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101}; // 26个质数
        List<List<String>> res = new ArrayList<>();
        HashMap<Integer, Integer> resMap = new HashMap<>(); // key是质数积，value是当前质数积在List res的位置(0,1,2)
        for (String s : strs) {
            int key = 1;
            for (char c : s.toCharArray())
                key *= prime[c - 'a'];

            if (resMap.containsKey(key))
                res.get(resMap.get(key)).add(s);

            else {
                List<String> curList = new ArrayList<>();
                curList.add(s);
                res.add(curList);
                resMap.put(key, res.size() - 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._049GroupAnagrams");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(true, true);
    }
}
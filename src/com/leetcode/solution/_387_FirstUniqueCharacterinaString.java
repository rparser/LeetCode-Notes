package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

import java.util.HashMap;

/**
 * 第一个不重复字符
 * 遍历两次，第一次添加进hashmap，第二次从hashmap中取出值为1的
 * <p>
 * Time complexity : O(N) since we go through the string of length N two times.
 * Space complexity : O(N) since we have to keep a hash map with N elements.
 */

public class _387_FirstUniqueCharacterinaString {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {//遍历第一次：遍历字符串
            count.put(c, count.getOrDefault(c, 0) + 1);//如果空值则添加0再+1即为1
        }
        for (int i = 0; i < s.length(); i++) {//遍历第二次，遍历hashmap
            if (count.get(s.charAt(i)) == 1) return i;//查找对应值返回第一个为1的字母（unique）
        }
        return -1;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._387_FirstUniqueCharacterinaString");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(4, firstUniqChar("aabbcddee"));
    }
}


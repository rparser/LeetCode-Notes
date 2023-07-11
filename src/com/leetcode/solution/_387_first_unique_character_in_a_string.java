package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 第一个不重复字符
 * 遍历两次，第一次添加进hashmap，第二次从hashmap中取出值为1的
 * <p>
 * Time complexity : O(N) since we go through the string of length N two times.
 * Space complexity : O(N) since we have to keep a hash map with N elements.
 */

public class _387_first_unique_character_in_a_string {
    // O(N), O(N)
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) //遍历第一次：遍历字符串
            count.put(c, count.getOrDefault(c, 0) + 1);//如果空值则添加0再+1即为1

        for (int i = 0; i < s.length(); i++)//遍历第二次，遍历hashmap
            if (count.get(s.charAt(i)) == 1) return i;//查找对应值返回第一个为1的字母（unique）

        return -1;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._387_FirstUniqueCharacterinaString");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(4, firstUniqChar("aabbcddee"));
    }

    // 统计所有不重复字母
    // Function to print distinct characters in
    // given string str[]
    static void printDistinct(String str) {
        int n = str.length();

        // count[x] is going to store count of
        // character 'x' in str. If x is not present,
        // then it is going to store 0.
        int[] count = new int[256];

        // index[x] is going to store index of character
        // 'x' in str. If x is not present or x is
        // more than once, then it is going to store a
        // value (for example, length of string) that
        // cannot be a valid index in str[]
        int[] index = new int[256];

        // Initialize counts of all characters and
        // indexes of distinct characters.
        for (int i = 0; i < 256; i++) {
            count[i] = 0;
            index[i] = n; // A value more than any
            // index in str[]
        }

        // Traverse the input string
        for (int i = 0; i < n; i++) {
            // Find current character and increment
            // its count
            char x = str.charAt(i);
            ++count[x];

            // If this is first occurrence, then set
            // value in index as index of it.
            if (count[x] == 1 && x != ' ')
                index[x] = i;

            // If character repeats, then remove it
            // from index[]
            if (count[x] == 2)
                index[x] = n;
        }

        // Since size of index is constant, below
        // operations take constant time.
        Arrays.sort(index);

        for (int i = 0; i < 256 && index[i] != n; i++)
            System.out.print(str.charAt(index[i]));
    }
}


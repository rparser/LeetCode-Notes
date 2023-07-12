package com.leetcode.solution;

/*
 * 一个26大小的count数组就够
 * 统计字母数目，一个字符串++，一个字符串--
 * 最后counter所有值的绝对值和的一半(因为有两个数组)就是结果
 * O(N),O(1)
 */

public class _1347_Minimum_Number_of_Steps_to_Make_Two_Strings_Anagram {
    public int minSteps(String s, String t) {
        int[] count = new int[26];
        for (char a : s.toCharArray()) {
            count[a - 'a']++;
        }
        for (char a : t.toCharArray()) {
            count[a - 'a']--;
        }

        int res = 0;
        for (int c : count) {
            res += Math.abs(c);
        }
        return res / 2; // 有两个数组所以结果/2
    }
}

package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

import java.util.*;

//按照字符串比较
public class _179LargestNumber {

    public String largestNumber(int[] nums) {
        String[] numsToString = new String[nums.length];
        for (int i = 0; i < nums.length; i++) numsToString[i] = Integer.toString(nums[i]);
        Arrays.sort(numsToString,
                (s1, s2) -> {
                    if (s1.length() == s2.length()) return s2.compareTo(s1);
                    return (s2 + s1).compareTo(s1 + s2);
                }); //lambda 表达式

        if ("0".equals(numsToString[0])) return "0";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numsToString.length; i++) {
            sb.append(numsToString[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._179LargestNumber");
    }

    @Test
    public void testSolution() {
        int[] input = {1, 9, 40, 55, 6};
        Assert.assertEquals("9655401", largestNumber(input));
    }

}

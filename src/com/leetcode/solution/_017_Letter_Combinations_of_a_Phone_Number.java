package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.*;

/**
 * 电话号码转换问题，经典backtracking回溯法
 * Time complexity : O(3^N×4^M) where N is the number of digits in the input that maps to 3 letters (e.g. 2, 3, 4, 5, 6, 8)
 * and M is the number of digits in the input that maps to 4 letters (e.g. 7, 9), and N+M is the total number digits in the input.
 * <p>
 * Space complexity : O(3^N×4^M) since one has to keep 3^Nx4^M solutions.
 */

public class _017_Letter_Combinations_of_a_Phone_Number {
    // java 9可以如此生成map
//    Map<String, String> map2 = Map.of("key1", "value1", "key2", "value2");
    // O(3^N×4^M), O(3^N×4^M)
    private final Map<String, String> phone = new HashMap<String, String>() {{
        put("0", "0");
        put("1", "1");
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};


    private void backtrack(String current, String digitsLeft, List<String> result) {
        // 已不剩余
        if (digitsLeft.length() == 0)
            result.add(current);
        else {
            // 取当前digitsLeft第一个数字对应的字母集
            String digit = digitsLeft.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                // 对每个字母依次加入递归
                String letter = phone.get(digit).substring(i, i + 1);
                backtrack(current + letter, digitsLeft.substring(1), result);
            }
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() != 0)
            backtrack("", digits, result);

        return result;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._017LetterCombinationsofaPhoneNumber");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals("aa", letterCombinations("234"));
    }
}

package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final Map<String, String> phone = new HashMap<>() {{
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


    private void backtrack(String curString, String digitsLeft, List<String> result) {
        // 已不剩余
        if (digitsLeft.isEmpty()) {
            result.add(curString);
        } else {
            // 取当前digitsLeft第一个数字对应的字母集
            String letters = phone.get(digitsLeft.substring(0, 1));
            for (int i = 0; i < letters.length(); i++) {
                // 对每个字母依次加入递归
                backtrack(curString + letters.charAt(i), digitsLeft.substring(1), result);
                // 不需要验证所以不需要remove
            }
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (!digits.isEmpty()) {
            backtrack("", digits, result);
        }
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

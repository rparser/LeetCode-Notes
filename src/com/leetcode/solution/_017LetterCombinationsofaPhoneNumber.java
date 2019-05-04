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

public class _017LetterCombinationsofaPhoneNumber {
    private Map<String, String> phone = new HashMap<String, String>() {{
//        put("0","0");
//        put("1","1");
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");

    }};

    List<String> result = new ArrayList<>();

    private void backtrack(String current, String next_digits) {
        if (next_digits.length() == 0) result.add(current);  // if there is no more digits to check

            // if there are still digits to check
        else {
            // iterate over all letters which map
            // the next available digit
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            System.out.println("   letters=" + letters);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                // append the current letter to the combination
                // and proceed to the next digits
                System.out.println(current + "    digit=" + digit + "   letter=" + letter);
                backtrack(current + letter, next_digits.substring(1));
            }
        }
    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0)
            backtrack("", digits);
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

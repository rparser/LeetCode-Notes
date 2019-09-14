package com.leetcode.solution;

import java.util.*;

/**
 * 时间复杂度
 * O(N)， 因为字符串只遍历了一次
 * Iterate through string. Use a variable to store the previous operation (+ / -). If it's -, that means I need to add (-1) * number. There are six cases:
 * Number: keep track of current number. Only perform an operation when I see + or -, because number can have more than one digit.
 * Space: do nothing.
 * +: add sign * number to result. Set sign to 1 and reset number.
 * -: add sign * number to result. Set sign to -1 and reset number.
 * (: Push previous result and sign onto stack. Reset result to start calculation inside parentheses.
 * ): Store result inside the parentheses into number. Pop the previous sign and result.
 * At the end, add sign * last number.
 */

public class _224_BasicCalculator {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>(); // to store last value and sign before any parentheses
        int result = 0; // current result within one priority (in the same parentheses)
        int sign = 1;
        int number = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) number = number * 10 + (c - '0');
            else if (c == '+') {
                result += sign * number;
                sign = 1;
                number = 0;
            } else if (c == '-') {
                result += sign * number;
                sign = -1;
                number = 0;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * number;
                number = result;
                sign = stack.pop();
                result = stack.pop();
            }
        }

        result += sign * number;
        return result;
    }
}

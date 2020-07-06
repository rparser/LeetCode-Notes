package com.leetcode.solution;

import java.util.*;

/**
 * Time complexity : O(n). nn is the length of the given string..
 * Space complexity : O(n). The size of stack can go up to nn.
 */

public class _032_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int result = 0, start = 0; //start为新括号的片段开始位置（之前已清空）
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push(i); //左括号则推入
            else {
                if (stack.isEmpty())
                    start = i + 1; //如果右括号且stack空，证明此时都无效，需要从下一位开始
                else {
                    stack.pop();
                    result = Math.max(result, stack.isEmpty() ? i - start + 1 : i - stack.peek());
                } //右括号且不为空，弹出，计算大小（如果stack变为空，则从之前的start开始，如果stack不为空则从最近一个peek开始）
            }
        }
        return result;
    }
}

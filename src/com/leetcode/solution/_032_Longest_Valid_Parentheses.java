package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Time complexity : O(n). n is the length of the given string..
 * Space complexity : O(n). The size of stack can go up to n.
 */

public class _032_Longest_Valid_Parentheses {
    // O(N), O(N)
    public int longestValidParentheses(String s) {
        //start为新括号的片段开始位置（之前已清空）
        int result = 0, start = 0;
        // 保存左括号的index
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            //左括号则推入
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            //如果右括号且stack空，证明此时都无效，需要从下一位开始
            else if (stack.isEmpty()) {
                start = i + 1;
            }
            //右括号且不为空，弹出计算大小
            else {
                stack.pop();
                // 如果弹出后变为空，则从之前的start开始
                // 如果stack不为空则从最近一个peek开始 ——因为左括号没有更新start,所以peek()才是准确值
                // 不需要+1因为——peek已经在之前一位了
                result = Math.max(result, stack.isEmpty() ? i - start + 1 : i - stack.peek());
            }
        }
        return result;
    }
}

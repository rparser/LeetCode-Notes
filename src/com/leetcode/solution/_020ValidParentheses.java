package com.leetcode.solution;

import java.util.*;

/**
 * 括号合法
 * 利用stack,看最近的open括号和当前close是否一致
 * <p>
 * Time complexity : O(n) because we simply traverse the given string one character at a time
 * and push and pop operations on a stack take O(1) time.
 * Space complexity : O(n) as we push all opening brackets onto the stack and in the worst case,
 * we will end up pushing all the brackets onto the stack. e.g. ((((((((((.
 */

public class _020ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
                if (stack.isEmpty()) return false; //说明没有open则返回否
                char cur = stack.pop(); //弹出第一个，看是否为对应的open
                if (cur == '(' && s.charAt(i) != ')') return false; //不是对应的open也返回否
                if (cur == '[' && s.charAt(i) != ']') return false;
                if (cur == '{' && s.charAt(i) != '}') return false;
            }
        }
        return stack.isEmpty();
    }
}

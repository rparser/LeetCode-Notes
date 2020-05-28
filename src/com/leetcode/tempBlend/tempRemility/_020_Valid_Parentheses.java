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

public class _020_ValidParentheses {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') { //左半括号推入stack
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') { //右半括号弹出查看
                if (stack.isEmpty()) return false; //说明没有open则返回否
                char cur = stack.pop(); //弹出第一个，看是否为对应的open
                if (cur == '(' && s.charAt(i) != ')') return false; //不是对应的open也返回否
                if (cur == '[' && s.charAt(i) != ']') return false;
                if (cur == '{' && s.charAt(i) != '}') return false;
            }
        }
        return stack.isEmpty();
    }

    public static List<Integer> check(String input) {
        char[] c = input.toCharArray();
        int value = 0;
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < c.length) {
            if (c[i] == '$') {
                j = i + 1;
                while (j < c.length && Character.isDigit(c[j]) || c[j] == ' ') {
                    j++;
                }
                if (j != i + 1) {
                    list.add(Integer.parseInt(input.substring(i + 1, j).trim()));
                    System.out.println(Integer.parseInt(input.substring(i + 1, j).trim()));
                }
            }
            i++;
        }
        return list;
    }
}

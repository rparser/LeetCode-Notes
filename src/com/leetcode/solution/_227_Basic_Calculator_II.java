package com.leetcode.solution;

import java.util.*;

/**
 * Time O(N)
 * Space O(N)
 * - use stack to store number after operation. initialize sign as '+'
 * - calculating current val = val * 10 + char
 * - when meet next sign, perform operation of last sign on current number(+,-*,/)
 * - finally sum up number in stack
 * eg: 2+33*2
 * (here) sign = +, val = 33, then sign = *
 */

public class _227_Basic_Calculator_II {
    //O(N), O(N)
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();

        char sign = '+';
        int cur = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c))
                cur = cur * 10 + (c - '0'); //如果是数字
            //如果是符号 或 最后一位 要push进stack
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (sign == '+')
                    stack.push(cur);
                if (sign == '-')
                    stack.push(-cur);
                if (sign == '*')
                    stack.push(stack.pop() * cur);
                if (sign == '/')
                    stack.push(stack.pop() / cur);

                sign = c;
                cur = 0;
            }
        }

        int result = 0;
        //处理stack所有的数值
        for (Integer i : stack)
            result += i;

        return result;
    }
}

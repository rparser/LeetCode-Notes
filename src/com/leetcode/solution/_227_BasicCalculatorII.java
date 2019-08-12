package com.leetcode.solution;

import java.util.Stack;

/**
 * 时间复杂度 O(N)
 * 空间复杂度 O(1)
 * 之前用stack, 但是这里其实只有两种优先级，所以， 每次遇到一个数的时候，可以先保存在一个prev变量中，然后如果遇到乘号或者除号，
 * 就对prev和当前的数做计算， 如果遇到加号或者减号，那么之前的乘号和除号的结果就已经结束了，可以合并到一个total里面。
 * 这里，提取数的小技巧和之前写的略有不同。 遇到一个digit以后，直接在里面在循环一次把所有的digit都遍历完。
 * 感觉这样写逻辑更加简单一点， 因为这样当前的数立刻就提取完了， 然后直接对这个数做处理会比较简单一点。
 */

public class _227_BasicCalculatorII {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int cur = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) cur = cur * 10 + (c - '0');
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (sign == '+') stack.push(cur);
                if (sign == '-') stack.push(-cur);
                if (sign == '*') stack.push(stack.pop() * cur);
                if (sign == '/') stack.push(stack.pop() / cur);
                sign = c;
                cur = 0;
            }
        }

        int res = 0;
        for (Integer i : stack) res += i;
        return res;
    }
}

package com.leetcode.solution;

import java.util.*;

/**
 * It’s trivial that we only use O(1) time for each iteration, so it will take O(n) time if n denotes to length of tokens.
 * For space complexity, stack should have ability to save all operands in tokens list.
 * Therefore, it uses O(m) extra space if m denotes to counts of operands in tokens list.
 * 逆波兰表达式的解释器一般是基于堆栈的。解释过程一般是：操作数入栈；遇到操作符时，操作数出栈，求值，将结果入栈；
 * 当一遍后，栈顶就是表达式的值。因此逆波兰表达式的求值使用堆栈结构很容易实现，并且能很快求值。
 */

public class _150_EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        if (tokens.length == 0) return 0;
        Stack<Integer> stack = new Stack<>(); // 第一个数有可能最后操作所以要FILO

        for (String str : tokens) {
            switch (str) {
                case "+": //如果是符号，则要计算后再push入
                    stack.push(stack.pop() + stack.pop());
                    break;

                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;

                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;

                case "/":
                    int n1 = stack.pop(), n2 = stack.pop();
                    stack.push(n2 / n1);
                    break;

                default:
                    stack.push(Integer.parseInt(str));
            }
        }
        return stack.pop();
    }
}

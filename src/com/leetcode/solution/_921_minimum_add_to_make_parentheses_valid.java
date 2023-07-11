package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

class _921_minimum_add_to_make_parentheses_valid {
    //O(N), O(1)
    // openLeft是目前还有多少左括号可用，openNeeded是需要多少右括号
    public int minAddToMakeValidCounter(String S) {
        int openLeft = 0;
        int openNeeded = 0;
        for (char c : S.toCharArray()) {
            if (c == '(')
                openLeft++;

            else if (c == ')') {
                if (openLeft > 0)
                    openLeft--;
                else
                    openNeeded++;
            }

        }
        //把剩余的左括号和需要的加一起
        return openNeeded + openLeft;
    }

    public int minAddToMakeValid(String S) {
        int result = 0;
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : S.toCharArray()) {
            if (c == '(') stack.push(c);
            else {
                if (!stack.isEmpty() && stack.peek() == '(')
                    stack.pop();
                else
                    result++;
            }
        }
        return result + stack.size();
    }
}
package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * O(N)~O(N)
 */

public class _856_Score_of_Parentheses {
    public int scoreOfParentheses(String s) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);
        for (char c : s.toCharArray()) {
            if (c == '(') {
                deque.addLast(0);
            } else {
                int cur = deque.pollLast();
                deque.addLast(deque.pollLast() + Math.max(cur * 2, 1));
            }
        }
        return deque.peekLast();
    }
}

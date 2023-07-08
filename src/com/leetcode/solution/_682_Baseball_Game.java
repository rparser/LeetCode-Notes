package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

class _682_Baseball_Game {
    public int calPoints(String[] ops) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : ops) {
            switch (s) {
                case "+" -> {
                    int t = stack.pop();
                    int score = t + stack.peek();
                    stack.push(t);
                    stack.push(score);
                }
                case "D" -> stack.push(stack.peek() * 2);
                case "C" -> stack.pop();
                default -> stack.push(Integer.parseInt(s));
            }
        }

        int sum = 0;
        for (int i : stack)
            sum += i;

        return sum;
    }
}

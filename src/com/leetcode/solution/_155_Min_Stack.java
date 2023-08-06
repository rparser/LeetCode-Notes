package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

class _155_Min_Stack {
    int min = Integer.MAX_VALUE;
    Deque<Integer> stack = new LinkedList<>();

    public void push(int x) {
        //当前值更小
        if (x <= min) {
            //将之前的最小值保存,再存到stack内一次，避免丢失
            stack.push(min);
            //更新最小值
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        //如果弹出的值是最小值，那么将下一个元素更新为最小值(之前的最小值)
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

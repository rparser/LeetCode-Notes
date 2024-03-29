package com.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

public class _225_Implement_Stack_using_Queues {
    private int tail;
    private final Queue<Integer> q;

    /**
     * Initialize your data structure here.
     */
    public _225_Implement_Stack_using_Queues() {
        q = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        tail = x;
        q.add(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     * 出栈的时间复杂度为 n
     */
    public int pop() {
        int size = q.size();
        for (int i = 0; i < size - 2; i++)
            q.add(q.remove());
        tail = q.remove(); // 堆顶第二个元素 让下次调用top时使用
        q.add(tail);
        return q.remove();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return tail;
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return q.isEmpty();
    }
}

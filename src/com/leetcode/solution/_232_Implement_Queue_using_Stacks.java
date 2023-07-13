package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

public class _232_Implement_Queue_using_Stacks {
    private final Deque<Integer> in;
    private final Deque<Integer> out;

    /**
     * Initialize your data structure here.
     */
    public _232_Implement_Queue_using_Stacks() {
        in = new LinkedList<>();
        out = new LinkedList<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        in.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (out.isEmpty()) {
            int size = in.size();
            for (int i = 0; i < size; i++)
                out.push(in.pop());
        }
        if (out.isEmpty()) return -1;
        else return out.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (out.isEmpty()) {
            int size = in.size();
            for (int i = 0; i < size; i++)
                out.push(in.pop());
        }
        return out.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}

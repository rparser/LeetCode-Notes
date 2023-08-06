package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

/**
 * For Deque,
 * from queue
 * add/offer -> addLast
 * remove/poll/element/peek -> removeFirst
 *
 * from Stack
 * push -> addFirst
 * pop/peek ->removeFirst
 */


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
        // 要先检查out里有没有东西，除非out已经空了，否则先用out里的（已经排序好了）
        if (out.isEmpty()) {
            inToOut();
        }
        if (out.isEmpty())
            return -1;
        else return out.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (out.isEmpty()) {
            inToOut();
        }
        if (out.isEmpty())
            return -1;
        else return out.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }

    private void inToOut(){
        while(!in.isEmpty()){
            out.push(in.pop());
        }
    }
}

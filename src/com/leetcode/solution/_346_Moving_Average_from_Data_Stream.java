package com.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

class _346_Moving_Average_from_Data_Stream {
    // O(1), O(N)
    private final Queue<Integer> q = new LinkedList<>();
    private int sum;
    private int size = 0;

    public _346_Moving_Average_from_Data_Stream(int size) {
        this.size = size;
    }

    public double next(int val) {
        // 大小达到size了则先remove再offer
        if (q.size() == size)
            sum -= q.remove();

        q.offer(val);
        sum += val;
        return (double) sum / q.size();
    }
}

package com.leetcode.solution;

import java.util.*;

/**
 * amortized O(n)
 */

public class _239_SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return nums;

        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!dq.isEmpty() && dq.peek() < i - k + 1) dq.poll(); // 移掉超出范围的
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) dq.pollLast(); // 如果k范围内的，新来的值比前值更小，移去前值
            dq.offer(i); // dq contains index... nums contains content
            if (i - k + 1 >= 0) result[i - k + 1] = nums[dq.peek()]; //更新结果
        }
        return result;
    }
}

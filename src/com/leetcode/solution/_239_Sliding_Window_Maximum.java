package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * amortized O(n)
 */

public class _239_Sliding_Window_Maximum {
    //双向单调递减队列 O(N), O(N)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return nums;
        int[] result = new int[n - k + 1];
        // dq 保存index
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // 移掉进入此loop时，队伍最头，超出index范围的
            if (!dq.isEmpty() && dq.peek() < i - k + 1)
                dq.pollFirst();
            // 如果k范围内的，新来的值比前值更小，移去前值
            // (比如[3,4],4进入后，3不可能是最大值，3会比4早离开window)
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()])
                dq.pollLast();
            // 当前值加入dq
            dq.offer(i);
            // 去队伍最头找(因为队列单调递减)
            if (i - k + 1 >= 0)
                result[i - k + 1] = nums[dq.peek()];
        }
        return result;
    }
}

package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

public class _862_shortest_subarray_with_sum_at_least_k {
    //    O(N), O(N)
    public int shortestSubarray(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return -1;
        int len = nums.length, min = len + 1;
        int[] sum = new int[len + 1];

        for (int i = 0; i < len; i++)
            sum[i + 1] = sum[i] + nums[i];

        Deque<Integer> queue = new ArrayDeque<>();
        // pollFirst() == poll()    较早放入deque的元素 在队列顶部
        // getFirst() == peek()
        // pollLast()               最近放入deque的元素 在队列尾部
        // getLast()
        for (int i = 0; i <= len; i++) {
            // 检查最近放入的index，保证队列中新放入的index及对应的sum[index]均为递增
            // 反证：若保留worse candidate，那么在下面第二个while循环，该元素有可能
            // 中断while循环，并使得我们无法得到队列更左边的最优解
            while (queue.size() > 0 && sum[i] - sum[queue.getLast()] <= 0)
                queue.pollLast();
            // 检查较早放入的index update最小距离
            while (queue.size() > 0 && sum[i] - sum[queue.peek()] >= k) {
                min = Math.min(min, i - queue.peek());
                queue.pollFirst();
            }
            queue.offer(i);
        }
        return min <= len ? min : -1;
    }
}

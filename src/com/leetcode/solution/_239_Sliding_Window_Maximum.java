package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

/**
 * amortized O(n)
 * 1, 先淘汰head被挤出去的
 * 2, 把当前值和tail开始比较，while当前值大于等于tail则踢掉tail
 * 3, 把当前值加入tail（保证head->tail递减）
 * 4, head为当前window最大值
 * 双向单调递减队列(head->tail递减)
 * O(N), O(N)
 */

public class _239_Sliding_Window_Maximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return nums;
        int[] result = new int[n - k + 1]; // 一共是n-k+1个窗口
        // dq 保存index - 不是值 是index
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int idxFirst = i - k + 1; // 当前窗口的第一个元素
            // 1,移掉进入此loop时，队伍head（最早进入的） 且 超出index范围的（被挤出去的）
            if (!dq.isEmpty() && dq.peekFirst() < idxFirst) {
                dq.pollFirst();
            }
            // 2,如果k范围内的，新来的值比前值更小，移去前值
            // 从队尾开始检查，所有当前值>=peekLast的都移出，直到当前值<peekLast，这样保证队尾到队首是升序->
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }
            // 3,当前值加入dq
            dq.offer(i);
            // 4,去队伍最头找最大值(因为head最大)
            if (idxFirst >= 0) {
                result[idxFirst] = nums[dq.peekFirst()];
            }
        }
        return result;
    }
}

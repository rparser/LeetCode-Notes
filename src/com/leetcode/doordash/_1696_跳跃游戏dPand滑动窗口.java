package com.leetcode.doordash;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 *
 * 一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。也就是说，
 * 你可以从下标 i 跳到 [i + 1， min(n - 1, i + k)] 包含 两个端点的任意位置。
 *
 * 你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。
 *
 * 请你返回你能得到的 最大得分 。
 *
 * 每次用一个变量maxWindow表示此前dp数组中的最大值，然后在第i个元素上，用dp[i - 1]与maxWindow比较，
 * 如果dp[i - 1]大于maxWindow，就可以将maxWindow的值更新为dp[i - 1]，这样的话，每次只需要进行一次比较，就可以解决问题了。
 *
 */

public class _1696_跳跃游戏dPand滑动窗口 {
    public int maxResult(int[] nums, int k) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        // 构造滑动窗口的索引所对应的队列，队首至队尾的索引依次增大，但对应dp数组中的值依次降低
        Deque<Integer> windowIndices = new LinkedList<>();
        for (int i = 1; i < nums.length; i++) {
            // 如果新的索引i所对应的元素dp[i - 1]大于队尾rear所对应的数组元素dp[rear]，就循环弹出队尾，直到新的元素i - 1能够成为队尾
            // 因为dp[rear] < dp[i - 1]且rear < i - 1，只要窗口继续向右移，dp[rear]就一定会被dp[i - 1]压在下面，不会成为窗口最大元素
            while (!windowIndices.isEmpty() && dp[i - 1] >= dp[windowIndices.peekLast()]) {
                windowIndices.pollLast();
            }
            windowIndices.offerLast(i - 1);
            if (windowIndices.peekFirst() < i - k) {
                windowIndices.pollFirst();
            }
            dp[i] = dp[windowIndices.peekFirst()] + nums[i];
        }
        return dp[nums.length - 1];
    }
}

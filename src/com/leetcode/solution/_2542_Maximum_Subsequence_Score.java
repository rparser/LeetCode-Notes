package com.leetcode.solution;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 方法：优先队列
 * 按照 nums2 降序排序，使得 nums2 变差
 * 收集 k 个元素，比较获得最优解
 * 超过 k 个时，提出一个和最小的进行替换
 */
public class _2542_Maximum_Subsequence_Score {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        long ans = 0L;
        Integer[] sorts = new Integer[n];
        for (int i = 0; i < n; i++) sorts[i] = i;
        Arrays.sort(sorts, (a, b) -> nums2[b] - nums2[a]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long sum = 0L;
        for (int i = 0; i < k - 1; i++) {
            sum += nums1[sorts[i]];
            pq.offer(nums1[sorts[i]]);
        }

        for (int i = k - 1; i < n; i++) {
            sum += nums1[sorts[i]];
            pq.offer(nums1[sorts[i]]);
            ans = Math.max(ans, nums2[sorts[i]] * sum);
            sum -= pq.poll();
        }

        return ans;
    }
}

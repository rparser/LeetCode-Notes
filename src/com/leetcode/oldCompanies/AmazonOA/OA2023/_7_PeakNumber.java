package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 我们使用了优先队列（最大堆），先从左到右迭代数组，再从右到左迭代数组。
 * 同时使用了两个boolean数组 leftFlag 和 rightFlag 来记录是否为“peak number”。
 * 然后在最后一次迭代中计算“peak number”的数量。实现与原始的Python代码逻辑相同，但使用了Java中的优先队列来模拟最大堆，从而实现了相同的功能。
 */

public class _7_PeakNumber {

    public static int findPeakNumberCount(int[] array, int k) {
        int n = array.length;
        boolean[] leftFlag = new boolean[n];
        boolean[] rightFlag = new boolean[n];
        int result = 0;

        // 从左到右迭代
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            offerToPq(array, k, leftFlag, maxHeap, i);
        }

        maxHeap.clear();

        // 从右到左迭代
        for (int i = n - 1; i >= 0; i--) {
            offerToPq(array, k, rightFlag, maxHeap, i);
        }

        // 计算 peak number 的数量
        for (int i = 0; i < n; i++) {
            if (leftFlag[i] && rightFlag[i]) {
                result++;
            }
        }

        return result;
    }

    private static void offerToPq(int[] array, int k, boolean[] leftFlag, PriorityQueue<Integer> maxHeap, int i) {
        int cur = array[i];
        // 如果还没填满先填满
        if (maxHeap.size() < k) {
            maxHeap.offer(cur);
        } else { // 如果cur比当前PQ的最大值要小，那就换位置，方便后来者-更容易满足（PQ更小了）
            if (cur < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(cur);
                // 填满之后如果大于，那肯定符合了，因为至少比PQ大小是k
            } else if (cur > maxHeap.peek()) {
                leftFlag[i] = true;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 8, 5, 3, 4};
        int k = 2;
        System.out.println(findPeakNumberCount(nums, k)); // Output: 2
        int[] nums2 = {1, 2, 5, 7, 9, 9, 9, 0, 8, 8,3, 1};
        int k2 = 3;
        System.out.println(findPeakNumberCount(nums2, k2)); // Output: 2
    }
}

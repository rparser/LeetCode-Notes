package com.leetcode.solution;

import java.util.*;

/**
 * 数组第K大值
 * <p>
 * Time complexity : O(Nlogk).
 * Space complexity : O(k) to store the heap elements.
 */


public class _215_KthLargestElementinanArray {
    public int findKthLargestPQ(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // keep k largest elements in the heap
        for (int n : nums) {
            pq.add(n);
            if (pq.size() > k)
                pq.poll();
        }

        // output
        return pq.poll();
    }

    /**
     * 快速排序
     * O(NlogN) time complexity and O(1) space complexity
     */

    public int findKthLargestQuickSort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * QuickSelect
     * Time complexity : O(N) in the average case
     * Space complexity : O(1).
     */

    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else break;
        }
        return nums[k];
    }

    private void shuffle(int[] arr) {
        final Random random = new Random();
        for (int ind = 1; ind < arr.length; ind++) {
            final int r = random.nextInt(ind + 1);
            swap(arr, ind, r);
        }
    }

    private int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (i < hi && less(a[++i], a[lo])) ;
            while (j > lo && less(a[lo], a[--j])) ;
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }


    private void swap(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private boolean less(int v, int w) {
        return v < w;
    }
}

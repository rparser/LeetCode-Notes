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

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        // 转换一下，第 k 大元素的索引是 len - k
        int target = len - k;

        while (true) {
            int index = partition(nums, left, right);
            if (index == target) return nums[index];
            else if (index < target) left = index + 1;
            else right = index - 1;
        }
    }

    /**
     * 在数组 nums 的子区间 [left, right] 执行 partition 操作，返回 nums[left] 排序以后应该在的位置
     * 在遍历过程中保持循环不变量的语义
     * 1、[left + 1, j] < nums[left]
     * 2、(j, i] >= nums[left]
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                // 小于 pivot 的元素都被交换到前面
                j++;
                swap(nums, j, i);
            }
        }
        // 在之前遍历的过程中，满足 [left + 1, j] < pivot，并且 (j, i] >= pivot
        swap(nums, j, left);
        // 交换以后 [left, j - 1] < pivot, nums[j] = pivot, [j + 1, right] >= pivot
        return j;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}

package com.leetcode.solution;

import java.util.*;

// 时间复杂度O(NK)移除时搜索元素 : O(NlogK)插入 -
// O(log(n)) time for the enqueing and dequeing methods (offer, poll, remove() and add); linear time for the remove(Object) and contains(Object) methods;

class _480_sliding_window_median {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((Collections.reverseOrder()));
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (maxHeap.size() == 0 || maxHeap.peek() >= nums[i])
                maxHeap.add(nums[i]);
            else
                minHeap.add(nums[i]);

            balanceHeaps();

            if (i - k + 1 >= 0)
                if (maxHeap.size() == minHeap.size())
                    result[i - k + 1] = maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
                else
                    result[i - k + 1] = maxHeap.peek();


                int elementToBeRemoved = nums[i - k + 1];

                if (elementToBeRemoved <= maxHeap.peek())
                    maxHeap.remove(elementToBeRemoved);
                else
                    minHeap.remove(elementToBeRemoved);

                balanceHeaps();
        }
        return result;
    }

    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.add(maxHeap.poll());
        else if (maxHeap.size() < minHeap.size())
            maxHeap.add(minHeap.poll());
    }
}
package com.leetcode.solution;

import java.util.*;

/**
 * add(num): O(logn)
 * findMedian(): O(1)
 * <p>
 * https://www.programcreek.com/2015/01/leetcode-find-median-from-data-stream-java/
 */

public class _295_FindMedianfromDataStream {
    PriorityQueue<Integer> minHeap = null;
    PriorityQueue<Integer> maxHeap = null;

    public _295_FindMedianfromDataStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        minHeap.offer(num);
        maxHeap.offer(minHeap.poll());

        if (minHeap.size() < maxHeap.size())
            minHeap.offer(maxHeap.poll());
    }

    public double findMedian() {
        if (minHeap.size() > maxHeap.size())
            return minHeap.peek();
        else
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

}

package com.leetcode.solution;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * add(num): O(logn)
 * findMedian(): O(1)
 * <p>
 * https://www.programcreek.com/2015/01/leetcode-find-median-from-data-stream-java/
 * 双PQ做法 - 每个PQ数量一致或minHeap多一个
 * 最小堆储存偏大的一半，最大堆储存偏小的一半
 */

public class _295_Find_Median_from_Data_Stream {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    // add(num): O(logn), findMedian O(1), space O(N)
    public _295_Find_Median_from_Data_Stream() {
        minHeap = new PriorityQueue<>(); //取出来的是最小值
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); //取出来的是最大值
    }

    public void addNum(int num) {
        minHeap.offer(num);
        maxHeap.offer(minHeap.poll()); // 把最小的给最大堆
        // 如果min比max小，则要移过去一个
        if (minHeap.size() < maxHeap.size()) {
            minHeap.offer(maxHeap.poll()); // 把最大的给最小堆
        }
    }

    public double findMedian() {
        // 如果为奇数，min会多一个，则中位数是min的最后一个
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

}

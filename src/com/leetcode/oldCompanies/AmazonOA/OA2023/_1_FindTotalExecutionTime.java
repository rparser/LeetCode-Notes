package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.List;
import java.util.PriorityQueue;

/**
 * O(n * log n)
 */
public class _1_FindTotalExecutionTime {

    public static int totalTime(List<Integer> jobs) {
        // Use a max heap by providing a custom comparator to reverse the natural order
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Add all jobs to the max heap
        for (Integer job : jobs) {
            maxHeap.offer(job);
        }

        int totalTime = 0;
        while (!maxHeap.isEmpty()) {
            int currentJob = maxHeap.poll();
            totalTime += currentJob; // Increment the total time by the current job's execution time

            // Reduce the execution time of cohesive jobs to ceil(job[i] / 2) and update the max heap
            while (!maxHeap.isEmpty() && maxHeap.peek() == currentJob) {
                int cohesiveJob = maxHeap.poll();
                int reducedExecutionTime = (int) Math.ceil(cohesiveJob / 2.0);
//                if (reducedExecutionTime > 0) {
                    maxHeap.offer(reducedExecutionTime);
//                }
            }
        }
        return totalTime;
    }

    public static void main(String[] args) {
        List<Integer> jobs = List.of(8, 8, 4,8);
        int result = totalTime(jobs);
        System.out.println("Total Time: " + result); // Output will be 15
    }
}

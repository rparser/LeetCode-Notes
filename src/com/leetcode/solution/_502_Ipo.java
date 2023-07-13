package com.leetcode.solution;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * time  O(NlogN + KlogN) at the most, all the projects will be pushed to both the heaps once
 * space O(N)O because we will be storing all the projects in the heaps.
 */

class _502_Ipo {
    public int findMaximizedCapital(int numberOfProjects, int initialCapital, int[] profits, int[] capital) {
        int n = profits.length;
        PriorityQueue<Integer> minCapitalHeap = new PriorityQueue<>(n, Comparator.comparingInt(i -> capital[i]));
        PriorityQueue<Integer> maxProfitHeap = new PriorityQueue<>(n, (i1, i2) -> profits[i2] - profits[i1]);

        // insert all project capitals to a min-heap
        for (int i = 0; i < n; i++)
            minCapitalHeap.offer(i);

        // let's try to find a total of 'numberOfProjects' best projects
        int availableCapital = initialCapital;
        for (int i = 0; i < numberOfProjects; i++) {
            // 每一次把可以投资的项目放进maxProfitHeap，不需要处理顺序因为只要找到最大的即可
            while (!minCapitalHeap.isEmpty() && availableCapital >= capital[minCapitalHeap.peek()])
                maxProfitHeap.add(minCapitalHeap.poll());

            // 已经没有新项目可投资时break
            if (maxProfitHeap.isEmpty())
                break;

            // select the project with the maximum profit
            availableCapital += profits[maxProfitHeap.poll()];
        }
        return availableCapital;
    }
}

package com.leetcode.solution;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class _826_Most_Profit_Assigning_Work {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int N = difficulty.length;
        Point[] jobs = new Point[N];
        for (int i = 0; i < N; i++) {
            jobs[i] = new Point(difficulty[i], profit[i]);
        }
        Arrays.sort(jobs, Comparator.comparingInt(a -> a.x));
        Arrays.sort(worker); // 工人和工作分别升序排序

        int result = 0, i = 0, bestProfitForThisWorker = 0;
        // bestProfitForThisWorker一定升序
        for (int skill : worker) {
            while (i < N && skill >= jobs[i].x) {
                bestProfitForThisWorker = Math.max(bestProfitForThisWorker, jobs[i].y);
                i++;
            }
            result += bestProfitForThisWorker;
        }
        return result;
    }
}

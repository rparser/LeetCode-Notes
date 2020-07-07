package com.leetcode.solution;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 距离原点最近的K点
 * Time Complexity: O(NlogN), where N is the length of points.
 * <p>
 * Space Complexity: O(N).
 */


public class _973_KClosestPointstoOrigin {
    public int[][] kClosestPQ(int[][] points, int K) {
        if (points == null || points.length == 0 || K < 1) return points;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> calculateDist(b) - calculateDist(a));
        for (int[] point : points) {
            pq.add(point);
            if (pq.size() > K) pq.poll();
        }

        int[][] result = new int[K][2];
        for (int i = 0; i < K; i++) result[i] = pq.poll();

        return result;
    }

    public int calculateDist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    /**
     * Divide and Conquer
     * 参考215
     * Time Complexity: O(N) in average case complexity, where N is the length of points.
     * Space Complexity: O(N).
     */
    public int[][] kClosest(int[][] points, int K) {
        partition(0, points.length - 1, points, K);
        int[][] ret = new int[K][2];
        if (K >= 0)
            System.arraycopy(points, 0, ret, 0, K);
        return ret;
    }

    private Random rd = new Random();

    private int partition(int st, int ed, int[][] points, int K) {
        if (st >= ed)
            return st;

        int id = rd.nextInt(ed - st + 1) + st;
        swap(id, ed, points);

        int j = ed - 1, pivot = calculateDist(points[ed]);

        for (int i = 0; i <= j; i++) {
            if (calculateDist(points[i]) > pivot) {
                swap(i, j--, points);
                i--;
            }
        }
        swap(ed, ++j, points);
        if (j == K - 1)
            return j;
        else if (j < K - 1)
            return partition(j + 1, ed, points, K);
        else
            return partition(st, j - 1, points, K);
    }

    private void swap(int a, int b, int[][] points) {
        int[] tmp = points[a];
        points[a] = points[b];
        points[b] = tmp;
    }
}

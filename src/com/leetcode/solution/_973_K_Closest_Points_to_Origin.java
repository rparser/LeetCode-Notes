package com.leetcode.solution;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 距离原点最近的K点
 * Time Complexity: O(NlogN), where N is the length of points.
 * <p>
 * Space Complexity: O(N).
 */


public class _973_K_Closest_Points_to_Origin {
    /**
     * Divide and Conquer
     * 参考215
     * Time Complexity: O(N) in average case complexity, where N is the length of points.
     * Space Complexity: O(N).
     */
    public int[][] kClosest(int[][] points, int K) {
        quickSort(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    private void quickSort(int[][] a, int low, int high, int K) {
        if (low < high) {
            int pivot = partition(a, low, high);
            if (pivot == K)
                return;
            if (pivot > K)
                quickSort(a, low, pivot - 1, K);
            else
                quickSort(a, pivot + 1, high, K);
        }
    }


    private int partition(int[][] a, int low, int high) {
        int[] pivot = a[low];
        int pivotDist = dist(pivot);

        while (low < high) {
            while (low < high && dist(a[high]) >= pivotDist)
                high--;

            a[low] = a[high];
            while (low < high && dist(a[low]) <= pivotDist)
                low++;

            a[high] = a[low];
        }
        a[low] = pivot;
        return low;
    }

    private int dist(int[] a) {
        return a[0] * a[0] + a[1] * a[1];
    }


    public int[][] kClosestPQ(int[][] points, int K) {
        if (points == null || points.length == 0 || K < 1) return points;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> dist(b) - dist(a));
        for (int[] point : points) {
            pq.add(point);
            if (pq.size() > K) pq.poll();
        }

        int[][] result = new int[K][2];
        for (int i = 0; i < K; i++) result[i] = pq.poll();

        return result;
    }
}

package com.leetcode.solution;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 距离原点最近的K点
 * Time Complexity: O(NlogN), where N is the length of points.
 * <p>
 * Space Complexity: O(N).
 */


public class _973KClosestPointstoOrigin {

    public int[][] kClosest(int[][] points, int K) {
        int N = points.length;
        int[] distToOrigin = new int[N];
        for (int i = 0; i < N; ++i)
            distToOrigin[i] = calculateDist(points[i]);

        Arrays.sort(distToOrigin);
        int distK = distToOrigin[K - 1];

        int[][] result = new int[K][2];
        int t = 0;
        for (int i = 0; i < N; ++i)
            if (calculateDist(points[i]) <= distK)
                result[t++] = points[i];
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

    int[][] points;

    public int[][] kClosestQuickSelect(int[][] points, int K) {
        this.points = points;
        sort(0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    public void sort(int i, int j, int K) {
        if (i >= j) return;
        int k = ThreadLocalRandom.current().nextInt(i, j);
        swap(i, k);

        int mid = partition(i, j);
        int leftLength = mid - i + 1;
        if (K < leftLength)
            sort(i, mid - 1, K);
        else if (K > leftLength)
            sort(mid + 1, j, K - leftLength);
    }

    public int partition(int i, int j) {
        int oi = i;
        int pivot = dist(i);
        i++;

        while (true) {
            while (i < j && dist(i) < pivot)
                i++;
            while (i <= j && dist(j) > pivot)
                j--;
            if (i >= j) break;
            swap(i, j);
        }
        swap(oi, j);
        return j;
    }

    public int dist(int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    public void swap(int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }
}
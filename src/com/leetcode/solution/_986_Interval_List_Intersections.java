package com.leetcode.solution;

import java.util.*;

/**
 * Time Complexity: O(M + N), where M, N are the lengths of A and B respectively.
 * Space Complexity: O(M + N), the maximum size of the answer.
 */

public class _986_IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            // Let's check if A[i] intersects B[j].
            // lo - the startpoint of the intersection取最大
            // hi - the endpoint of the intersection取最小
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            if (lo <= hi) result.add(new int[]{lo, hi});

            // Remove the interval with the smallest endpoint
            if (A[i][1] < B[j][1]) i++;
            else j++;
        }
        return result.toArray(new int[result.size()][]);
    }
}
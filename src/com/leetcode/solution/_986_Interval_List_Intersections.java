package com.leetcode.solution;

import java.util.*;

/**
 * Time Complexity: O(M + N), where M, N are the lengths of A and B respectively.
 * Space Complexity: O(M + N), the maximum size of the answer.
 */

public class _986_Interval_List_Intersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            //起始点取最大 结束点取最小
            // lo - the startpoint of the intersection
            // hi - the endpoint of the intersection
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            if (lo <= hi)
                result.add(new int[]{lo, hi});

            // Remove the interval with the smallest endpoint
            if (A[i][1] < B[j][1])
                i++;
            else
                j++;
        }
        return result.toArray(new int[result.size()][]);
    }
}

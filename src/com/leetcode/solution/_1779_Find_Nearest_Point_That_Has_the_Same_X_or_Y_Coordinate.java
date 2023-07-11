package com.leetcode.solution;

public class _1779_Find_Nearest_Point_That_Has_the_Same_X_or_Y_Coordinate {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int minDistance = Integer.MAX_VALUE, idx = -1, n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i][0] != x && points[i][1] != y)
                continue; // 如果x和y都不相等，直接下一个point
            int curDistance = Math.abs(x - points[i][0]) + Math.abs(y - points[i][1]); // 否则计算新的距离

            if (curDistance < minDistance) {
                minDistance = curDistance;
                idx = i;
            }
        }
        return idx;
    }
}

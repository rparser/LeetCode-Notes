package com.leetcode.solution;

import java.util.*;

public class _939_minimum_area_rectangle {
    //O(N ^2),O(N) 面积最小矩形，且边平行x,y轴
    // 40001是题目要求各点都小于40000，可以转成一维,字符串算法见下
    //找对角线Diagonal
    public int minAreaRect(int[][] points) {
        Set<Integer> pointSet = new HashSet<>();
        for (int[] point : points)
            pointSet.add(40001 * point[0] + point[1]);

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; ++i)
            for (int j = i + 1; j < points.length; ++j)
                //如果是不同的两个点 且 有另外的两个点
                if (points[i][0] != points[j][0] && points[i][1] != points[j][1]
                        && pointSet.contains(40001 * points[i][0] + points[j][1])
                        && pointSet.contains(40001 * points[j][0] + points[i][1]))
                    //更新result
                    result = Math.min(result, Math.abs(points[j][0] - points[i][0])
                            * Math.abs(points[j][1] - points[i][1]));

        return result < Integer.MAX_VALUE ? result : 0;
    }

    public int minAreaRectString(int[][] points) {
        Set<String> pointSet = new HashSet<>();
        for (int[] point : points)
            pointSet.add(point[0] + "," + point[1]);

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; ++i)
            for (int j = i + 1; j < points.length; ++j)
                if (points[i][0] != points[j][0] && points[i][1] != points[j][1]
                        && pointSet.contains(points[i][0] + "," + points[j][1])
                        && pointSet.contains(points[j][0] + "," + points[i][1]))

                    result = Math.min(result, Math.abs(points[j][0] - points[i][0])
                            * Math.abs(points[j][1] - points[i][1]));

        return result < Integer.MAX_VALUE ? result : 0;
    }
}

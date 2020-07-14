package com.leetcode.solution;

import java.util.*;

class _452_minimum_number_of_arrows_to_burst_balloons {
    // O(NlogN)排序，O(1) 类似合并区间
    // 如果要射爆所有气球，必须射爆第一个气球，最佳是射在第一个气球的最右侧
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        //贪心思想 - 有重叠的气球用一箭
        //将气球右边界升序排序
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));

        int shotNum = 1;
        int lastEnd = points[0][1];

        for (int i = 1; i < points.length; i++) {
            // 有重叠，这一箭可以射掉所有continue里的
            if (points[i][0] <= lastEnd)
                continue;
            //没有重叠时，更新最右边界，需要多射一箭
            lastEnd = points[i][1];
            shotNum++;
        }
        return shotNum;
    }
}

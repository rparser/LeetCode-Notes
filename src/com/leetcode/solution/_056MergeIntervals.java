package com.leetcode.solution;

import java.util.*;

/**
 * 合并间隔问题
 * 1. 根据间隔起点排序，并存入第一个间隔
 * 2. 如果当前间隔的起点小于之前间隔的终点，则出现“交叉”，则合并交叠
 * 3. 否则加入新的间隔
 * <p>
 * Time complexity : O(nlogn)
 * Other than the sort invocation, we do a simple linear scan of the list,
 * so the runtime is dominated by the O(nlgn) complexity of sorting.
 * <p>
 * Space complexity : O(1)
 * If we can sort intervals in place, we do not need more than constant additional space.
 * Otherwise, we must allocate linear space to store a copy of intervals and sort that.
 */

public class _056MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0 || intervals[0].length == 0) return new int[][]{};
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt((a) -> (a[0])));
        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] lastInterval = result.get(result.size() - 1); // 取得结果最后一个间隔
            int[] currentInterval = intervals[i]; // 取得当前间隔
            // 如果当前间隔的起点小于之前间隔的终点，则出现“交叉”，则合并交叠
            if (currentInterval[0] <= lastInterval[1]) {
                lastInterval[0] = Math.min(lastInterval[0], currentInterval[0]);
                lastInterval[1] = Math.max(lastInterval[1], currentInterval[1]);
            } else {
                result.add(currentInterval); // 否则没有交叠，加入新的间隔
            }
        }
        int[][] res = new int[result.size()][2];
        res = result.toArray(res);
        return res;
    }

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}

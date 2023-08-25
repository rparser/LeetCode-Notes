package com.leetcode.solution;

import java.util.Arrays;
import java.util.Comparator;

public class _436_Find_Right_Interval {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        // startIdxs由n个[start_i, idx]组成
        int[][] startIdxs = new int[n][2];
        for (int i = 0; i < n; i++) {
            int start = intervals[i][0];
            startIdxs[i] = new int[]{start, i};
        }
        // 将startIdx按start升序排序，后续好在startIdx中进行二分查找
        Arrays.sort(startIdxs, Comparator.comparingInt(a -> a[0]));
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int end = intervals[i][1];
            // 在startIdx中查找第一个大于等于end的start值，并返回对应的idx
            ans[i] = find(startIdxs, end);
        }
        return ans;
    }

    /**
     * 在startIdx中查找第一个大于等于end的start值，并返回对应的idx
     * @param startIdxs
     * @param target
     * @return 找到这个值，返回对应的idx；否则返回-1
     */
    private int find(int[][] startIdxs, int target) {
        int left = 0, right = startIdxs.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (startIdxs[mid][0] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left == startIdxs.length ? -1 : startIdxs[left][1];
    }
}

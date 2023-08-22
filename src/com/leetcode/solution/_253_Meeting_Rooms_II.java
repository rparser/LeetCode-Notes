package com.leetcode.solution;

import java.util.Arrays;

/**
 * 最小堆minHeap，先按start排序，然后维护一个minHeap，堆顶元素是会议结束时间最早的区间，也就是end最小。
 * 每次比较top元素的end时间和当前元素的start时间，如果end < start，
 * 说明该room可以结束接下来被当前会议区间使用。最后返回堆的大小就是所需的房间数。
 */


public class _253_Meeting_Rooms_II {
    // O(nlogn), O(n)
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        if (n < 1) {
            return 0;
        }
        int[] begin = new int[n], end = new int[n];
        for (int i = 0; i < n; i++) {
            begin[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(begin);
        Arrays.sort(end);
        int result = 0;
        int i = 0, j = 0, cur = 0;
        // 看成上下车, 每次上车都看下是否有乘客已经下车了,下车几个
        // 本题可以看成车上同时最多有多少个乘客
        while (i < n) {
            if (begin[i] < end[j]) { //此时是乘客上车
                cur++;
                i++;
            } else { //此时乘客下车
                cur--;
                j++;
            }
            result = Math.max(result, cur);
        }
        return result;
    }
}

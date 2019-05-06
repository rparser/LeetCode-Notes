package com.leetcode.solution;

import java.util.*;

/**
 *最小堆minHeap，先按start排序，然后维护一个minHeap，堆顶元素是会议结束时间最早的区间，也就是end最小。
 * 每次比较top元素的end时间和当前元素的start时间，如果end < start，
 * 说明该room可以结束接下来被当前会议区间使用。最后返回堆的大小就是所需的房间数。
 *
 */


public class _253_MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        int n=intervals.length;
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for (int i=0; i<n; i++) {
            if (i>0 && intervals[i][0]>=pq.peek()) pq.poll();
            pq.add(intervals[i][1]);
        }
        return pq.size();
    }
}

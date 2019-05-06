package com.leetcode.solution;

import java.util.*;

public class _252_MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
        for(int i=0; i<intervals.length-1; i++){
            if(intervals[i][1]>intervals[i+1][0]){
                return false;
            }
        }
        return true;
    }
}

package com.leetcode.solution;

import java.util.*;

public class _056MergeIntervals {

      public class Interval {
   int start;
     int end;
      Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
  }
//    private class IntervalComparator implements Comparator<Interval> {//先implements interface
//        @Override
//        public int compare(Interval a, Interval b) {//重写compare方法
//            return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;//-1是a<b,0是a=b,+1是a>b
//        }
//    }


    public List<Interval> merge(List<Interval> intervals) {
//        Collections.sort(intervals, new IntervalComparator()); //先排序，用刚定义的comparator
        Collections.sort(intervals, (a,b)->
            a.start < b.start ? -1 : a.start == b.start ? 0 : 1);
        LinkedList<Interval> merged = new LinkedList<>();//储存结果
        for (Interval interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast().end < interval.start) { //结果的屁股值是不是比新来的头小，如果是开一个新片段
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast().end = Math.max(merged.getLast().end, interval.end); //不断在屁股上加东西
            }
        }
        return merged;
    }
}

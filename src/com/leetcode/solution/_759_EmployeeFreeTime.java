package com.leetcode.solution;

import java.util.*;

public class _759_EmployeeFreeTime {
    public int[][] employeeFreeTime(int[][][] schedule) {
        List<int[]> result = new ArrayList<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < schedule.length - 1; i++)
            for (int j = 0; j < schedule[i].length - 1; j++)
                pq.offer(schedule[i][j]);

        int[] temp = pq.poll();
        while (!pq.isEmpty()) {
            if (temp[1] < pq.peek()[0]) { // no intersect
                result.add(new int[]{temp[1], pq.peek()[0]});
                temp = pq.poll(); // becomes the next temp interval
            } else { // intersect or sub merged
                temp = temp[1] < pq.peek()[1] ? pq.peek() : temp;
                pq.poll();
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    class Interval {
        public int start;
        public int end;

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {

        List<Interval> result = new ArrayList<>();

        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        avails.forEach(e -> pq.addAll(e));

        Interval temp = pq.poll();
        while (!pq.isEmpty()) {
            if (temp.end < pq.peek().start) { // no intersect
                result.add(new Interval(temp.end, pq.peek().start));
                temp = pq.poll(); // becomes the next temp interval
            } else { // intersect or sub merged
                temp = temp.end < pq.peek().end ? pq.peek() : temp;
                pq.poll();
            }
        }
        return result;
    }
}

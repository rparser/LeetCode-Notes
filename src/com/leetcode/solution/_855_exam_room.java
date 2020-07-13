package com.leetcode.solution;

import java.util.*;

public class _855_exam_room {
    // TreeSet升序，为了找到left most和right most的学生  O(P)，其中 P 是当前入座学生的数目
    int capacity;
    TreeSet<Integer> students;

    public _855_exam_room(int N) {
        this.capacity = N;
        students = new TreeSet<>();
    }

    public int seat() {
        // toSit是即将要坐的位置
        int toSit = 0;
        if (students.size() > 0) {
            // 找到最左的学生
            int distance = students.first();
            // 假设有学生坐 -1
            int prev = -1;
            for (int s : students) {
                // 如果有距离更远的两点，则更新distance
                int tmpDis = (s - prev) / 2;
                if (tmpDis > distance) {
                    distance = tmpDis;
                    toSit = prev + tmpDis;
                }
                prev = s;
            }
            // 如果最后空了很多，-1是index所以-1,直接坐到最后一位
            if (capacity - 1 - students.last() > distance)
                toSit = capacity - 1;
        }
        // 学生入座
        students.add(toSit);
        return toSit;
    }

    public void leave(int p) {
        students.remove(p);
    }
}

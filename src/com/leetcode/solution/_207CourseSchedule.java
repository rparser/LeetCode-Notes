package com.leetcode.solution;

import java.util.*;

/**
 *  有向图课程
 *
 * [2,0]为上2课必须先上0
 */

public class _207CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) { //requisite (re-kwi-zit)
        int[] inDegree = new int[numCourses]; //顶点inDegree值 (当前课需要几门先上的课)
        if (prerequisites == null || prerequisites.length == 0) return true;
        HashMap<Integer, List<Integer>> graph = new HashMap<>(); //num->list, 上了num后可以上哪些课

        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][0]]++; //依次设置inDegree
            if (graph.containsKey(prerequisites[i][1])) //如果图里已有，则加入列表
                graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            else {
                ArrayList<Integer> list = new ArrayList<>(); //否则创建新list，加入i值，再加入到graph里
                list.add(prerequisites[i][0]);
                graph.put(prerequisites[i][1], list);
            }
        }

        LinkedList<Integer> queue = new LinkedList<>(); //初始化完毕，建立queue

        for (int i = 0; i < numCourses; i++)
            if (inDegree[i] == 0) queue.offer(i); //把indegree为0的先加入queue

        while (!queue.isEmpty()) { //从queue中不断poll
            int course = queue.poll();
            List<Integer> subcourses = graph.get(course);
            for (int i = 0; subcourses != null && i < subcourses.size(); i++)
                if (--inDegree[subcourses.get(i)] == 0) queue.offer(subcourses.get(i)); //每门课被取出一次indegree减1
            //当减到0时，加入到queue
        }

        for (int i = 0; i < numCourses; i++) //如果有不为0的返回false
            if (inDegree[i] != 0) return false;
        return true;
    }
}
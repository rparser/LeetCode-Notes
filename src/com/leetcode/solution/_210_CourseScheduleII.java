package com.leetcode.solution;

import java.util.*;

public class _210_CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i)
            graph.add(new ArrayList<>()); //构造无向图

        for (int[] i : prerequisites) {
            int course = i[0];
            int prerequisite = i[1];
            graph.get(course).add(prerequisite); //变为有向图
        }

        int[] visited = new int[numCourses]; //已访问列表(0未访问，1正在访问，2已访问)
        List<Integer> ans = new ArrayList<>(); 
        for (int i = 0; i < numCourses; ++i)
            if (dfs(i, graph, visited, ans)) return new int[0];

        return ans.stream().mapToInt(i -> i).toArray();
    }

    private boolean dfs(int curr, ArrayList<ArrayList<Integer>> graph, int[] visited, List<Integer> ans) {
        if (visited[curr] == 1) return true; // 1为正在访问，则有环
        if (visited[curr] == 2) return false; // 2为已访问，false跳过
        visited[curr] = 1; //变为正在访问
        for (int next : graph.get(curr))
            if (dfs(next, graph, visited, ans)) return true; //遍历所有，如果有true则为true有环

        visited[curr] = 2; // 无环变为2
        ans.add(curr); // 加入结果
        return false;
    }
}

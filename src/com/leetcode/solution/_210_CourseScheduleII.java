package com.leetcode.solution;

import java.util.*;

public class _210_CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i)
            graph.add(new ArrayList<>());

        for (int[] i : prerequisites) {
            int course = i[0];
            int prerequisite = i[1];
            graph.get(course).add(prerequisite);
        }

        int[] visited = new int[numCourses];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i)
            if (dfs(i, graph, visited, ans)) return new int[0];

        return ans.stream().mapToInt(i -> i).toArray();
    }

    private boolean dfs(int curr, ArrayList<ArrayList<Integer>> graph, int[] visited, List<Integer> ans) {
        if (visited[curr] == 1) return true;
        if (visited[curr] == 2) return false;
        visited[curr] = 1;
        for (int next : graph.get(curr))
            if (dfs(next, graph, visited, ans)) return true;

        visited[curr] = 2;
        ans.add(curr);

        return false;
    }
}

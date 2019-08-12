package com.leetcode.solution;

import java.util.*;

/**
 * DFS
 * Time complexity: O(n!)
 * Space complexity: O(n)
 */

public class _797_AllPathsFromSourcetoTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(res, graph, 0, path);
        return res;
    }

    private void dfs(List<List<Integer>> res, int[][] graph, int cur, List<Integer> path) {
        if (cur == graph.length - 1) { //到达终点则return
            res.add(new ArrayList<>(path));
            return;
        }
        for (int next : graph[cur]) {
            path.add(next); //加入可能方向往下递归
            dfs(res, graph, next, path);
            path.remove(path.size() - 1); //回溯删除最近添加的
        }
    }
}

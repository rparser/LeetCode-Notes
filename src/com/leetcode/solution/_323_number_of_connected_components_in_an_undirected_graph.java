package com.leetcode.solution;

import java.util.*;

class _323_number_of_connected_components_in_an_undirected_graph {
    // Union and Find O(N),O(N)
    public int countComponents(int n, int[][] edges) {
        // parents of 每个vertex(vertices)
        int[] parents = new int[n];
        Arrays.fill(parents, -1);

        return union(parents, edges);
    }

    private int union(int[] parents, int[][] edges) {
        // 假设all unconnected
        int count = parents.length;
        for (int[] e : edges) {
            int root1 = find(parents, e[0]);
            int root2 = find(parents, e[1]);
            // root不等变相等后，count--证明少了一个unconnected_components
            if (root1 != root2) {
                // parents[root2] = root1也可以
                parents[root1] = root2;
                count--;
            }
        }
        return count;
    }

    private int find(int[] parents, int x) {
        int root = x;
        while (parents[root] != -1)
            root = parents[root];

        return root;
    }


    //DFS
    public int countComponentsDFS(int n, int[][] edges) {
        int count = 0;
        List<List<Integer>> adjList = new ArrayList<>();
        boolean[] visited = new boolean[n];
        // 每个vertex(vertices)的相邻集合
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());
        //双向加入
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        for (int i = 0; i < n; i++)
            if (!visited[i]) {
                count++;
                dfs(visited, i, adjList);
            }
        return count;
    }

    private void dfs(boolean[] visited, int index, List<List<Integer>> adjList) {
        // 访问到即visited
        visited[index] = true;
        for (int i : adjList.get(index))
            // 如果没有访问过继续recursive call
            if (!visited[i])
                dfs(visited, i, adjList);
    }


    // BFS
    public int countComponentsBFS(int n, int[][] edges) {
        int count = 0;
        List<List<Integer>> adjList = new ArrayList<>();
        boolean[] visited = new boolean[n];
        // 每个vertex(vertices)的相邻集合
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());
        //双向加入
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        for (int i = 0; i < n; i++) {
            // 如果没有访问过则count++,访问过则跳过
            if (!visited[i]) {
                count++;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                // 所有connected的点都在这个while loop里
                while (!queue.isEmpty()) {
                    int index = queue.poll();
                    visited[index] = true;
                    for (int next : adjList.get(index))
                        // 把unvisited加入queue while loop
                        if (!visited[next])
                            queue.offer(next);
                }
            }
        }
        return count;
    }
}

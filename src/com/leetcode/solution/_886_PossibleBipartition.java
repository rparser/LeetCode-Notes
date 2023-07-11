package com.leetcode.solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Solution: Graph Coloring
 * Color a node with one color, and color all it’s disliked nodes with another color, if can not finish return false.
 * Time complexity: O(V+E)
 * Space complexity: O(V+E)
 * 时间复杂度
 * 每个节点访问一次， 每个dislike也访问一次， 相当于O(N+E), N是节点个数， E是边的个数
 * 空间复杂度
 * 新建了一个allList, 相当于E2的大小, 然后一个groupTable的大小是N, 那就是O(E2+N)
 */

public class _886_PossibleBipartition {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[][] graph = new int[N][N];
        for (int[] d : dislikes) {
            graph[d[0] - 1][d[1] - 1] = 1;
            graph[d[1] - 1][d[0] - 1] = 1;
        }
        int[] group = new int[N];
        for (int i = 0; i < N; i++)
            if (group[i] == 0 && dfs(graph, group, i, 1)) return false;
        return true;
    }

    private boolean dfs(int[][] graph, int[] group, int index, int g) {
        group[index] = g;
        for (int i = 0; i < graph.length; i++) {
            if (graph[index][i] == 1) {
                if (group[i] == g) return true;
                if (group[i] == 0 && dfs(graph, group, i, -g)) return true;
            }
        }
        return false;
    }

    public boolean possibleBipartitionBfs(int N, int[][] dislikes) {
        int[] color = new int[N + 1];
        List<List<Integer>> adj = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int[] d : dislikes) {
            adj.get(d[0]).add(d[1]);
            adj.get(d[1]).add(d[0]);
        }
        for (int i = 1; i <= N; i++) {
            if (color[i] == 0) {
                color[i] = 1;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                while (!q.isEmpty()) {
                    int cur = q.poll();
                    for (int nb : adj.get(cur)) {
                        if (color[nb] == 0) {
                            color[nb] = color[cur] == 1 ? 2 : 1;
                            q.add(nb);
                        } else if (color[nb] == color[cur]) return false;
                    }
                }
            }
        }
        return true;
    }
}

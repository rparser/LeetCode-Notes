package com.leetcode.solution;


import java.util.LinkedList;
import java.util.Queue;

public class _547_friend_circles {
    //O(n^2), O(n)
    public int findCircleNumBFS2(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        //遍历每个node
        for (int i = 0; i < M.length; i++)
            if (visited[i] == 0) {
                count++;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                while (!queue.isEmpty()) {
                    int friend = queue.poll();
                    //访问过则直接继续
                    if (visited[friend] == 1)
                        continue;
                    visited[friend] = 1;
                    // 对称所以只需处理一半
                    for (int j = i; j < M.length; j++)
                        //认识且没有访问过
                        if (M[friend][j] == 1 && visited[j] == 0)
                            queue.add(j);
                }
            }
        return count;
    }

    //O(n^2), O(n)
    public int findCircleNum(int[][] M) {
        int count = 0;
        int n = M.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++)
            if (!visited[i]) {
                count++;
                visited[i] = true;
                dfs(M, i, visited);
            }
        return count;
    }

    private void dfs(int[][] M, int i, boolean[] visited) {
        for (int j = 0; j < M.length; j++)
            if (!visited[j] && M[i][j] == 1) {
                visited[j] = true;
                dfs(M, j, visited);
            }
    }
}

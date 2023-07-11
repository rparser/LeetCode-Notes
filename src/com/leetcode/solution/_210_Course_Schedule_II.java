package com.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Time complexity: O(V+E)
 * Space complexity: O(V+E)
 * 时间复杂度是O(N)，空间复杂度是O(N)。超过了100%的提交。
 * 这里记录一下DFS的做法：
 * a. 建立图， 用Map<Integer, Set > graph表示一个图， 其实里面可以用list
 * b. 然后对每个节点进行深度优先遍历， 然后遍历的时候要记录和更新状态
 * 要判断一个有向图是否有环， 状态可以定义成三个
 * a. 没有访问过： 所有没有访问过的节点都需要进行一次DFS
 * b. 正在访问： 表示从某一个节点开始DFS的时候，经过某一个节点的时候，设置这个节点为正在访问
 * c. 已经访问过了: 表示某一个节点已经访问完成， 实际在代码里面就是这个节点指向的后序节点都已经访问过了， 这时候就可以把当前节点标记成已经访问过了。
 * 那么，判断环就比较直接。 如果在访问某一个节点的时候, 如果一个节点正在访问，后序在这个遍历的过程中有回到这个正在访问的节点，那么一定是因为出现了环。 因为正常无环的有向图， 在DFS遍历的时候是不会访问到一个节点两次的。 整个遍历过程应该都是单向的.
 */

public class _210_Course_Schedule_II {
    // DFS做法 时间复杂度: O(n+m)，其中n 为课程数，m为先修课程的要求数 空间复杂度: O(n+m)
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
            if (dfs(i, graph, visited, ans))
                return new int[0]; //出现环则返回新数组

        return ans.stream().mapToInt(i -> i).toArray();
    }

    private boolean dfs(int curr, ArrayList<ArrayList<Integer>> graph, int[] visited, List<Integer> ans) {
        // 1为正在访问，则有环
        if (visited[curr] == 1)
            return true;
        // 2为已访问，false跳过
        if (visited[curr] == 2)
            return false;

        visited[curr] = 1; //变为正在访问
        for (int next : graph.get(curr))
            if (dfs(next, graph, visited, ans))
                return true; //遍历所有，如果有true则为true有环

        visited[curr] = 2; // 无环变为2
        ans.add(curr); // 加入结果
        return false;
    }
}

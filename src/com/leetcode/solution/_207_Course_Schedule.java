package com.leetcode.solution;

import java.util.*;

/**
 * 有向图课程
 * <p>
 * [2,0]为上2课必须先上0
 * 直接参考210
 */

public class _207_Course_Schedule {
    //想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1] 反的不要搞错
    //O(V+E), O(V+E) 课程安排图是否是 有向无环图(DAG) Directed acyclic graph
    private final int VISITED_THIS_LOOP = 1;
    private final int VISITED_PREVIOUS_LOOP = -1;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i)
            graph.add(new ArrayList<>());

        for (int[] i : prerequisites) {
            int course = i[0];
            int prerequisite = i[1];
            graph.get(course).add(prerequisite);
        }

        int[] visited = new int[numCourses];
        // 有环返回true 没有环返回false
        for (int i = 0; i < numCourses; ++i)
            if (dfs(i, graph, visited))
                return false;

        return true;
    }

    // 有环返回true 没有环返回false
    private boolean dfs(int curr, ArrayList<ArrayList<Integer>> graph, int[] visited) {
        if (visited[curr] == VISITED_THIS_LOOP) return true;
        if (visited[curr] == VISITED_PREVIOUS_LOOP) return false;
        // 标记为已访问
        visited[curr] = VISITED_THIS_LOOP;

        for (int next : graph.get(curr))
            if (dfs(next, graph, visited))
                return true;

        // 如果离开dfs没有return,标记为之前访问过，return false
        visited[curr] = VISITED_PREVIOUS_LOOP;
        return false;
    }

    public boolean canFinishBFS(int numCourses, int[][] prerequisites) { //requisite (re-kwi-zit)
        int[] inDegree = new int[numCourses]; //顶点inDegree值 (当前课需要几门先上的课)
        if (prerequisites == null || prerequisites.length == 0) return true;
        HashMap<Integer, List<Integer>> graph = new HashMap<>(); //num->list, 上了num后可以上哪些课

        for (int[] i : prerequisites) {
            inDegree[i[0]]++; //依次设置inDegree
            if (graph.containsKey(i[1]))
                graph.get(i[1]).add(i[0]); //如果图里已有，则加入列表
            else {
                ArrayList<Integer> list = new ArrayList<>(); //否则创建新list，加入i值，再加入到graph里
                list.add(i[0]);
                graph.put(i[1], list);
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

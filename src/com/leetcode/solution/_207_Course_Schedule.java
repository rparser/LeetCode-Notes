package com.leetcode.solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 有向图课程
 * <p>
 * [2,0]为上2课必须先上0
 * 直接参考210
 */

public class _207_Course_Schedule {
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
        for (int i = 0; i < numCourses; ++i) {
            if (dfs(i, graph, visited)) {
                return false;
            }
        }
        return true;
    }

    // 有环返回true 没有环返回false
    private boolean dfs(int curr, ArrayList<ArrayList<Integer>> graph, int[] visited) {
        //想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1] 反的不要搞错
        //O(V+E), O(V+E) 课程安排图是否是 有向无环图(DAG) Directed acyclic graph
        int VISITED_THIS_LOOP = 1;
        if (visited[curr] == VISITED_THIS_LOOP) {
            return true;
        }
        int VISITED_PREVIOUS_LOOP = -1;
        if (visited[curr] == VISITED_PREVIOUS_LOOP) {
            return false;
        }
        // 标记为已访问
        visited[curr] = VISITED_THIS_LOOP;

        for (int next : graph.get(curr)) {
            if (dfs(next, graph, visited)) {
                return true;
            }
        }
        // 如果离开dfs没有return,标记为之前访问过，return false
        visited[curr] = VISITED_PREVIOUS_LOOP;
        return false;
    }

    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses]; // 入度表(此课程需要多少list是此节点的prerequisites)
        List<List<Integer>> graph = new ArrayList<>(); // index是节点，list是此index的是哪些课的prerequisites
        Queue<Integer> queue = new LinkedList<>(); // 统计入度为0的节点
        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());
        // Get the inDegree and adjacency of every course.
        for (int[] p : prerequisites) {
            inDegrees[p[0]]++;
            graph.get(p[1]).add(p[0]);
        }
        // 找到入度为0的节点index，说明这些课不需要prerequisites就可以上
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }
        // BFS TopSort.
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--; //需要上的课的数量-1
            for (int cur : graph.get(pre)) { //因为已经上完了pre,get graph里的pre list对应的节点
                inDegrees[cur]--; // 此节点入度-1
                if (inDegrees[cur] == 0) { // 入度表变0可以加入队列
                    queue.add(cur);
                }
            }
        }
        return numCourses == 0; //如果全上完了则为true
    }

//    public boolean canFinishBFS2(int numCourses, int[][] prerequisites) { //requisite (re-kwi-zit)
//        int[] inDegree = new int[numCourses]; //顶点inDegree值 (当前课需要几门先上的课)
//        if (prerequisites == null || prerequisites.length == 0) return true;
//        HashMap<Integer, List<Integer>> graph = new HashMap<>(); //num->list, 上了num后可以上哪些课
//
//        for (int[] i : prerequisites) {
//            inDegree[i[0]]++; //依次设置inDegree
//            if (graph.containsKey(i[1]))
//                graph.get(i[1]).add(i[0]); //如果图里已有，则加入列表
//            else {
//                ArrayList<Integer> list = new ArrayList<>(); //否则创建新list，加入i值，再加入到graph里
//                list.add(i[0]);
//                graph.put(i[1], list);
//            }
//        }
//
//        LinkedList<Integer> queue = new LinkedList<>(); //初始化完毕，建立queue
//
//        for (int i = 0; i < numCourses; i++)
//            if (inDegree[i] == 0) queue.offer(i); //把indegree为0的先加入queue
//
//        while (!queue.isEmpty()) { //从queue中不断poll
//            int course = queue.poll();
//            List<Integer> subcourses = graph.get(course);
//            for (int i = 0; subcourses != null && i < subcourses.size(); i++)
//                if (--inDegree[subcourses.get(i)] == 0) queue.offer(subcourses.get(i)); //每门课被取出一次indegree减1
//            //当减到0时，加入到queue
//        }
//
//        for (int i = 0; i < numCourses; i++) //如果有不为0的返回false
//            if (inDegree[i] != 0) return false;
//        return true;
//    }
}

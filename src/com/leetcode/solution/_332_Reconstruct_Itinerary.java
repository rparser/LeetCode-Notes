package com.leetcode.solution;

import java.util.*;

//时间复杂度应为 O(|V|+|E|)，还要记得对临接点排序的时间复杂度 O(|E|log|E|)，算法整体时间复杂度为 O(|E|log|E|)；
// 如果整个图是链式的，那么调用栈最深，空间复杂度应为 O(|E|)。

class _332_Reconstruct_Itinerary {
    private static final String START_CITY = "JFK";

    public List<String> findItinerary(List<List<String>> tickets) {
        // 因为逆序插入，所以用链表
        List<String> result = new LinkedList<>();
        if (tickets == null || tickets.size() == 0)
            return result;
        // Key 为顶点，List<String> 为临接点, PQ是为了按照字母排序
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (List<String> itinerary : tickets) {
            // 如果这个stop没有，加入这个nextStops到graph
            PriorityQueue<String> nextStops = graph.computeIfAbsent(itinerary.get(0), k -> new PriorityQueue<>());
            //加入nextStop按字母排序
            nextStops.add(itinerary.get(1));
        }
        visit(graph, START_CITY, result);
        return result;
    }

    // DFS方式遍历图，当走到不能走为止，再将节点加入到答案
    private void visit(Map<String, PriorityQueue<String>> graph, String from, List<String> result) {
        Deque<String> stack = new ArrayDeque<>();
        stack.push(from);

        while (!stack.isEmpty()) {
            PriorityQueue<String> nextStops;
            // 这一步完成DFS可以通到的计算，如果全部联通则这while已经完成所有的计算
            while ((nextStops = graph.get(stack.peek())) != null && nextStops.size() > 0)
                stack.push(nextStops.poll());
            // 例子[["JFK","AAA"],["JFK","BBB"],["BBB","JFK"]]
            // 逆序插入,所以可能的孤岛在最后,此时会把AAA先加入结果，再返回，由于stack里面还有BBB，所以再计算BBB，加到AAA的前面
            result.add(0, stack.pop());
        }
    }
}

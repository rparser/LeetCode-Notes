package com.leetcode.solution;

import java.util.*;

/**
 * DFS
 * 递归或者使用Stack，对于DFS，每次clone一个node的时候，
 * 就要把它所有的neighbor加入到新clone的node的neighbor中，
 * 此时recursive调用dfs，如果没有visited过 - 新建一个node，否则直接从map中找到返回
 */

public class _133_Clone_Graph {
    // 已经处理 DFS O(N), O(N)
    private final Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
        Node cloneNode = new Node(node.val, new ArrayList<>());
        // 哈希表存储
        visited.put(node, cloneNode);

        // 遍历该节点的邻居并更新克隆节点的邻居列表
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }

    public Node cloneGraphIterative(Node node) {
        if (node == null) return node;
        Queue<Node> queue = new LinkedList<>();
        Map<Integer, Node> map = new HashMap<>();
        queue.offer(node);
        //先生成第一个节点
        Node n = new Node();
        n.val = node.val;
        n.neighbors = new ArrayList<Node>();
        map.put(n.val, n);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node temp : cur.neighbors) {
                //没有生成的节点就生成
                if (!map.containsKey(temp.val)) {
                    n = new Node();
                    n.val = temp.val;
                    n.neighbors = new ArrayList<Node>();
                    map.put(n.val, n);
                    queue.offer(temp);
                }
                map.get(cur.val).neighbors.add(map.get(temp.val));
            }
        }
        return map.get(node.val);
    }

    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }
}

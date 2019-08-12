package com.leetcode.solution;

import java.util.*;

/**
 * DFS
 * 递归或者使用Stack，对于DFS，每次clone一个node的时候，
 * 就要把它所有的neighbor加入到新clone的node的neighbor中，
 * 此时recursive调用dfs，如果没有visited过 - 新建一个node，否则直接从map中找到返回
 */

public class _133_CloneGraph {
    private HashMap<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        map.put(node, new Node(node.val, new ArrayList<>()));

        for (Node neighbor : node.neighbors) {
            if (!map.containsKey(neighbor)) map.get(node).neighbors.add(cloneGraph(neighbor)); //不存在neighbor则递归add neighbor
            else map.get(node).neighbors.add(map.get(neighbor)); //map已存在neighbor则直接加入neighbors
        }
        return map.get(node);
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}

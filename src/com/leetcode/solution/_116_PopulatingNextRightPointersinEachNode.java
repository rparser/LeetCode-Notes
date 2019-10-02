package com.leetcode.solution;

import java.util.*;

/**
 * 思路1： use recursion,
 * Complexity: O(n)time  --  visit each node to set up next pointer.
 * O(logN) space -- each node up to logN nodes from ancestor to itself
 * <p>
 * 思路2: Iterative, need to check cur.next is null, eg rightmost node on level
 * Complexity: O(N)-time O(1)-space
 */

public class _116_PopulatingNextRightPointersinEachNode {
    public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> nodes = new LinkedList<>();
        nodes.offer(root);

        while (!nodes.isEmpty()) {
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                Node cur = nodes.poll();
                Node n = null;
                if (i < size - 1) n = nodes.peek();

                cur.next = n;
                if (cur.left != null) nodes.offer(cur.left);
                if (cur.right != null) nodes.offer(cur.right);
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) return root;
        Node level_start = root;
        while (level_start != null) {
            Node cur = level_start;
            while (cur != null) {
                if (cur.left != null) cur.left.next = cur.right;
                if (cur.right != null && cur.next != null) cur.right.next = cur.next.left;
                cur = cur.next;
            }
            level_start = level_start.left;
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}

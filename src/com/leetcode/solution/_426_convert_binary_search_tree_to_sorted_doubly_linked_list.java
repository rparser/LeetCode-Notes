package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

class _426_convert_binary_search_tree_to_sorted_doubly_linked_list {
    //    递归
    Node first;
    Node pre;

    //    用一个first指针指向表头，
//    一个pre指针指向上一个节点（遍历完之后pre指针即指向表尾）。
    public Node treeToDoublyListRecursive(Node root) {
        if (root == null) return null;
        helper(root);
        //连接头尾
        first.left = pre;
        pre.right = first;
        return first;
    }

    public void helper(Node node) {
        if (node == null) return;

        //中序遍历先访问左子树
        helper(node.left);

        //访问当前节点
        //first若为空则赋值，first只赋值一次
        if (first == null)
            first = node;

        //pre为空则赋值
        if (pre != null) {
            pre.right = node;
            node.left = pre;
        }
        pre = node;
        //最后访问右子树
        helper(node.right);
    }

    // 迭代在中序遍历的基础上，
//    用一个first指针指向表头，
//    一个pre指针指向上一个节点（遍历完之后pre指针即指向表尾）。

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Deque<Node> stack = new ArrayDeque<>();
        do {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                Node node = stack.pop();
                //first若为空则赋值，first只赋值一次
                if (first == null) {
                    first = node;
                }
                //pre为空则赋值
                if (pre != null) {
                    pre.right = node;
                    node.left = pre;
                }
                pre = node;
                root = node.right;
            }
        } while (!stack.isEmpty() || root != null);
        //连接头尾
        first.left = pre;
        pre.right = first;
        return first;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}

package com.leetcode.solution;

import java.util.*;

public class _173_BinarySearchTreeIterator {
    private Deque<TreeNode> stack;

    public _173_BinarySearchTreeIterator(TreeNode root) {
        stack = new LinkedList<>();// you can also inialize at var declaration
        pushNode(root);//adding the node leftbranch to get the min
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode cur = stack.pop();
        int res = cur.val;
        pushNode(cur.right);//when remove a node, need to add its right node's left branch
        return res;
    }

    /*adding the node with its left branch*/
    public void pushNode(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

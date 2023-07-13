package com.leetcode.solution;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class _653_Two_Sum_IV_Input_is_a_BST {
    private final Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null)
            return false;
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    public boolean findTarget2(TreeNode root, int k) {
        if (root == null) return false;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            boolean result = search(root, cur, k - cur.val); // cur.val is x, we are here to find y
            if (result) return true;
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
        }
        return false;
    }

    private boolean search(TreeNode root, TreeNode cur, int y) {
        // binary seach on a BST(root), find node.val == y && node != cur
        while (root != null) {
            if (root.val == y && root != cur) {
                return true;
            } else if (y < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return false;
    }
}

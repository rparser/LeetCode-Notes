package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

class _538_Convert_BST_to_Greater_Tree {
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum = sum + root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    public TreeNode convertBST2(TreeNode root) {
        int sum = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.right;
            }
            node = stack.pop();
            sum = node.val + sum;
            node.val = sum;
            node = node.left;
        }
        return root;
    }
}

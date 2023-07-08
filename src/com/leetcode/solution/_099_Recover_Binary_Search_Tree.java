package com.leetcode.solution;

import java.util.Stack;

class _099_Recover_Binary_Search_Tree {
    TreeNode first = null;
    TreeNode second = null;

    public void recoverTree(TreeNode root) {
        inorderTraversal(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    TreeNode pre = null;

    private void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        if (pre != null && root.val < pre.val) {
            //第一次遇到逆序对
            if (first == null) {
                first = pre;
                //第二次遇到逆序对
            }
            second = root;
        }
        pre = root;
        inorderTraversal(root.right);
    }

    public void inorderTraversalStack(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && root.val < pre.val) {
                if (first == null) {
                    first = pre;
                }
                second = root;
            }
            pre = root;
            root = root.right;
        }
    }
}

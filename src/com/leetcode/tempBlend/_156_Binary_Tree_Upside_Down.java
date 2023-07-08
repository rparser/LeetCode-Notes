package com.leetcode.tempBlend;

import com.leetcode.solution.TreeNode;

import java.util.Stack;

class _156_Binary_Tree_Upside_Down {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) return root;
        TreeNode ans = helper(root.left, root);
        //防止形成环
        root.left = null;
        root.right = null;
        return ans;
    }

    public TreeNode helper(TreeNode left, TreeNode p) {
        TreeNode ans;
        if (left.left == null) {
            ans = left;
        } else {
            ans = helper(left.left, left);
        }
        left.left = p.right;
        left.right = p;
        return ans;
    }

    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ans = null;
        TreeNode cur = root;
        //找最左子树
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        ans = stack.pop();
        cur = ans;
        //反转
        while (!stack.isEmpty()) {
            cur.right = stack.pop();
            cur.left = cur.right.right;
            cur = cur.right;
        }
        //防止形成环
        cur.left = null;
        cur.right = null;
        return ans;
    }
}

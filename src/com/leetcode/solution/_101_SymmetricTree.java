package com.leetcode.solution;

/**
 * 思路： use recursion to recursively find if nodes in left & right subtree match till leaves.
 * helper(left.left ,right.right) && helper(left.right, right.left);
 */

public class _101_SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode leftN, TreeNode rightN) {
        if (leftN == null || rightN == null) return leftN == rightN;//need both be null
        if (leftN.val != rightN.val) return false;
        return helper(leftN.left, rightN.right) && helper(leftN.right, rightN.left);
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

package com.leetcode.solution;

/**
 * 113子问题
 * 树路径和到某个值
 * DFS递归（左/右，sum-root），sum-root==0则返回T
 */

public class _112_PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && sum - root.val == 0) //到叶子时为此值则返回T
            return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

package com.leetcode.solution;

/**
 * DFS，树最大直径（距离最远两点）
 * find out the max of leftDepth & rightDepth while at each node, meanwhile update the total max.
 * Complexity: Time O(N) Space O(N)
 */

public class _543_DiameterofBinaryTree {
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        max = Math.max(max, left + right); //比较当前深度和最大max的最大值
        return 1 + Math.max(left, right); //当前深度+1
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

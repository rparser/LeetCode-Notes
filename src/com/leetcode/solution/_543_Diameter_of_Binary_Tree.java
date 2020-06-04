package com.leetcode.solution;

/**
 * DFS，树最大直径（距离最远两点）
 * find out the max of leftDepth & rightDepth while at each node, meanwhile update the total max.
 * Complexity: Time O(N) Space O(N)
 */

public class _543_DiameterofBinaryTree {
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    // 函数dfs的作用是：找到以root为根节点的二叉树的最大深度
    public int dfs(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = dfs(root.left);
        int rigthDepth = dfs(root.right);
        res = Math.max(res, leftDepth + rigthDepth);
        return Math.max(leftDepth, rigthDepth) + 1;
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

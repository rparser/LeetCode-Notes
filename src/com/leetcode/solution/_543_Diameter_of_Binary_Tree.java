package com.leetcode.solution;

/**
 * DFS，树最大直径（距离最远两点）
 * find out the max of leftDepth & rightDepth while at each node, meanwhile update the total max.
 * Complexity: Time O(N) Space O(N)
 */
// O(N), O(N)实际问题，找到最大深度
public class _543_Diameter_of_Binary_Tree {
    int max = 0;
    int res;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    // dfs -> 找到以root为根节点的二叉树的最大深度
    public int dfs(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = dfs(root.left);
        int rightDepth = dfs(root.right);

        res = Math.max(res, leftDepth + rightDepth);

        return Math.max(leftDepth, rightDepth) + 1;
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

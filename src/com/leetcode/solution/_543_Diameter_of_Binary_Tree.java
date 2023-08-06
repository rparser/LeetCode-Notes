package com.leetcode.solution;

/**
 * DFS，树最大直径（距离最远两点）
 * find out the max of leftDepth & rightDepth while at each node, meanwhile update the total max.
 * Complexity: Time O(N) Space O(N)
 */
// O(N), O(N)实际问题，找到最大深度
public class _543_Diameter_of_Binary_Tree {
    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    // dfs -> 找到以root为根节点的二叉树的最大深度
    public int dfs(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = dfs(root.left); // 左子树最大链长+1
        int rightDepth = dfs(root.right); // 右子树最大链长+1
        // left,right都是边的数目
        res = Math.max(res, leftDepth + rightDepth); // 两条链拼成路径
        // +1是连到root的那条边
        return Math.max(leftDepth, rightDepth) + 1; // 当前子树最大链长
    }
}

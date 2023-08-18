package com.leetcode.solution;

/**
 * 先做98. 验证二叉搜索树，用后序遍历实现。
 */
public class _1373_Maximum_Sum_BST_in_Binary_Tree {
    private int result; // 二叉搜索树可以为空

    public int maxSumBST(TreeNode root) {
        dfs(root);
        return result;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) { // fake BST，0 可以是任意值
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        int[] left = dfs(node.left); // 递归左子树
        int[] right = dfs(node.right); // 递归右子树
        int x = node.val;
        if (x <= left[1] || x >= right[0]) { // 如果大于右子树最小值或小于左子树最大值 不是BST
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
        }

        int sum = left[2] + right[2] + x; // 这棵子树的所有节点值之和
        result = Math.max(result, sum);

        return new int[]{Math.min(left[0], x), Math.max(right[1], x), sum};
    }
}

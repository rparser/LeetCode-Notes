package com.leetcode.solution;

/**
 * DFS树最大长度
 * 思路： At every node, the max path formed by node's val + left child path (>0) + right child path(>0)
 * Since we can only have 1 parent node in path, when we do recursive call,
 * - in dfsPath: for each child node, we add the node's val with either its left or right path or nothing.
 * - keep track the max vs path sum pass through current node as the parent node
 * <p>
 * Complexity：  Time O(n)
 * Space O(h)
 */

public class _124_BinaryTreeMaximumPathSum {
    int maxPath = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfsPath(root);
        return maxPath;
    }

    //Path pass parent node only once
    //at every node, either add its left or right path or nth for n
    private int dfsPath(TreeNode root) {
        if (root == null) return 0;
        int sum1 = Math.max(0, dfsPath(root.left)); // 0代表如果负值就不继续
        int sum2 = Math.max(0, dfsPath(root.right));
        maxPath = Math.max(maxPath, sum1 + sum2 + root.val); // update max_sum if it's better to start a new path
        return root.val + Math.max(sum1, sum2); //返回当前分支的长度
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

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
 * 模板dfs,int(TreeNode)函数，左右max(0,递归(root.left),更新max=max(max,left+right+root)，返回root+max(left,right)
 */


public class _124_Binary_Tree_Maximum_Path_Sum {
    int maxPath = Integer.MIN_VALUE;
    // O(N), O(H)
    public int maxPathSum(TreeNode root) {
        dfsPath(root);
        return maxPath;
    }

    //Path pass parent node only once
    //at every node, either add its left or right path or nth for n
    private int dfsPath(TreeNode root) {
        if (root == null)
            return 0;

        int sum1 = Math.max(0, dfsPath(root.left)); // 0代表如果负值就不继续
        int sum2 = Math.max(0, dfsPath(root.right));

        maxPath = Math.max(maxPath, sum1 + sum2 + root.val); // update max_sum if it's better to start a new path

        return root.val + Math.max(sum1, sum2); //返回当前分支的长度 - root + 左子树或右子树，取较大值作为当前分支
    }
}

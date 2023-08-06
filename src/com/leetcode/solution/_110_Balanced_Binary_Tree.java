package com.leetcode.solution;

class _110_Balanced_Binary_Tree {
    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null)
            return 0;
        int left = recur(root.left);
        int right = recur(root.right);
        // 当左（右）有一个返回-1就证明整个树都已经不平衡
        if (left == -1 || right == -1)
            return -1;

        //当节点root 左右子树的高度差≥2 ：则返回 -1 ，代表此子树不是平衡树
        // 当节点root 左 / 右子树的深度差<2(还是平衡树): 则返回root的深度，即节点 root 的左右子树的深度最大值+1(本root)
        return Math.abs(left - right) >= 2 ? -1 : Math.max(left, right) + 1;
    }
}
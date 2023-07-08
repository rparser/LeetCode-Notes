package com.leetcode.solution;

class _110_Balanced_Binary_Tree {
    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) return 0;
        int left = recur(root.left);
        // 当左（右）子树高度 left== -1 时，代表此子树的 左（右）子树 不是平衡树，因此直接返回 -1−1 ；
        if (left == -1) return -1;
        int right = recur(root.right);
        if (right == -1) return -1;
        //当节点root 左 / 右子树的高度差 \geq 2≥2 ：则返回 -1−1 ，代表 此子树不是平衡树
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
}
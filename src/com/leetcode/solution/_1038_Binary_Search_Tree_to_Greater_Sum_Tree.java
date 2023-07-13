package com.leetcode.solution;

class _1038_Binary_Search_Tree_to_Greater_Sum_Tree {
    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        if (root != null) {
            bstToGst(root.right);
            sum = sum + root.val;
            root.val = sum;
            bstToGst(root.left);
        }
        return root;
    }
}

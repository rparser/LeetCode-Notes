package com.leetcode.solution;

public class _114_FlattenBinaryTreetoLinkedList {
    public void flatten(TreeNode root) {
        if (root == null) return;

        TreeNode leftn = root.left;
        TreeNode rightn = root.right;

        flatten(leftn);
        flatten(rightn);

        root.left = null;//remove the left node
        root.right = leftn; //(ex: 3)
        TreeNode cur = root; //(ex: 2)
        while (cur.right != null) cur = cur.right;
        cur.right = rightn;
        //(ex:3)      (ex:4)
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

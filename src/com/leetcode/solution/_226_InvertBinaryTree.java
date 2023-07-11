package com.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 思路1： Recursion - when meeting null or leaf nodes, we don't swap.
 * Then we keep swaping the left subtree and right subtree (1. traverse 2. reassign value)
 * Complexity: time O(N) space O(H)
 * <p>
 * 思路2： Iteration - level order traverse. keep swaping left child and right child of current node
 */

public class _226_InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;
        TreeNode left = invertTree(root.left); //如果左右不为空
        TreeNode right = invertTree(root.right); //1-23-4567 先翻转45再翻转67再翻转23
        root.left = right;
        root.right = left;
        return root;
    }

    public TreeNode invertTreeIteration(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            TreeNode left = cur.left;
            TreeNode right = cur.right;
            cur.left = right;
            cur.right = left;
            if (right != null) q.offer(right);//here order not matter
            if (left != null) q.offer(left);
        }
        return root;
    }
}

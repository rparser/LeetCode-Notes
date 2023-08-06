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

public class _226_Invert_Binary_Tree {
    public TreeNode invertTree(TreeNode root) {
        //递归函数的终止条件，节点为空时返回
        if (root == null) {
            return null;
        }
        //下面三句是将当前节点的左右子树交换
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        //递归交换当前节点的 左子树
        invertTree(root.left);
        //递归交换当前节点的 右子树
        invertTree(root.right);
        //函数返回时就表示当前这个节点，以及它的左右子树
        //都已经交换完了
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

package com.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 思路： use recursion to recursively find if nodes in left & right subtree match till leaves.
 * helper(left.left ,right.right) && helper(left.right, right.left);
 */

public class _101_Symmetric_Tree {
    // O(N), O(N)
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            // 每次出队两个节点 node1 和 node2
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            // 首先比较 node1 与 node2 这两个节点的值是否相等
            if (node1 == null && node2 == null)
                continue;

            if (node1 == null || node2 == null || node1.val != node2.val)
                return false;
            // 再将 node1 的左节点与 node2 的右节点一起入队（以便两节点一起出队，进行比较）
            queue.offer(node1.left);
            queue.offer(node2.right);
            // 再将 node1 的右节点与 node2 的左节点一起入队（以便两节点一起出队，进行比较）
            queue.offer(node1.right);
            queue.offer(node2.left);
        }
        return true;
    }

    // recursion   O(N), O(H) ~ O(logN) ~O(N)
    public boolean isSymmetricRecursion(TreeNode root) {
        if (root == null) return true;
        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode leftN, TreeNode rightN) {
        if (leftN == null || rightN == null) return leftN == rightN;//need both be null
        if (leftN.val != rightN.val) return false;
        return helper(leftN.left, rightN.right) && helper(leftN.right, rightN.left);
    }
}

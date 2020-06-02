package com.leetcode.solution;

import java.util.*;

/**
 * 验证BST，中序遍历
 * <p>
 * Time complexity : O(N) in the worst case when the tree is BST or the "bad" element is a rightmost leaf.
 * Space complexity : O(N) to keep stack.
 */
public class _098ValidateBinarySearchTree {
    long pre = Long.MIN_VALUE;
    // inorder
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        // 访问左子树
        if (!isValidBST(root.left)) return false;
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) return false;

        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }

    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        long pre = Long.MIN_VALUE;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
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

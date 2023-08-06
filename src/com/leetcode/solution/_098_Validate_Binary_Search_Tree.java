package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 验证BST，中序遍历
 * <p>
 * Time complexity : O(N) in the worst case when the tree is BST or the "bad" element is a rightmost leaf.
 * Space complexity : O(N) to keep stack.
 */
public class _098_Validate_Binary_Search_Tree {
    long pre = Long.MIN_VALUE;

    // inorder O(N), O(N)
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 实际就是inorder遍历 - 找左下最小值
        // 当前节点必须大于inorder前一个节点，否则说明不满足BST，返回 false
        if (pre >= root.val) {
            return false;
        }
        // 更新pre值 第一个为左下的最小值
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }

    public boolean isValidBSTIterative(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        long inorder = Long.MIN_VALUE;
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
}

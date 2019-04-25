package com.leetcode.solution;

import java.util.*;

/**
 * 前序遍历
 * <p>
 * Time complexity : we visit each node exactly once, thus the time complexity is O(N), where N is the number of nodes, i.e. the size of tree.
 * Space complexity : depending on the tree structure, we could keep up to the entire tree, therefore, the space complexity is O(N).
 */

public class _144BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return output;
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

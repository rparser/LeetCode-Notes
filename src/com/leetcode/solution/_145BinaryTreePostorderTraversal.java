package com.leetcode.solution;

import java.util.*;

/**
 * 后序遍历
 * <p>
 * Time complexity : we visit each node exactly once, thus the time complexity is O(N), where NN is the number of nodes, i.e. the size of tree.
 * Space complexity : depending on the tree structure, we could keep up to the entire tree, therefore, the space complexity is O(N).
 */

public class _145BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.addFirst(node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
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

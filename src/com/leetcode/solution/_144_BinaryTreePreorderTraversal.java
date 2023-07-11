package com.leetcode.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 前序遍历
 * <p>
 * Time complexity : we visit each node exactly once, thus the time complexity is O(N), where N is the number of nodes, i.e. the size of tree.
 * Space complexity : depending on the tree structure, we could keep up to the entire tree, therefore, the space complexity is O(N).
 * 参考255
 */

public class _144_BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> output = new Stack<>();
        if (root == null) return output;

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            output.push(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return new ArrayList<>(output);
    }
}

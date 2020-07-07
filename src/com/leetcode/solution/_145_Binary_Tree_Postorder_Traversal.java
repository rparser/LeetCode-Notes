package com.leetcode.solution;

import java.util.*;

/**
 * 后序遍历
 * <p>
 * Time complexity : we visit each node exactly once, thus the time complexity is O(N), where NN is the number of nodes, i.e. the size of tree.
 * Space complexity : depending on the tree structure, we could keep up to the entire tree, therefore, the space complexity is O(N).
 */

public class _145_Binary_Tree_Postorder_Traversal {
    // Iterative O(N), O(N) 后序左子，右子，parent
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();
        // stack保持先进后出
        Deque<TreeNode> stack = new ArrayDeque<>();

        if (root == null)
            return output;

        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // addFirst所以root第一个加但在output末尾
            output.addFirst(node.val);
            // 右子树要先加入结果，所以在后面
            if (node.left != null)
                stack.push(node.left);

            if (node.right != null)
                stack.push(node.right);
        }
        return output;
    }

    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }

    private void helper(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        helper(root.left, list);
        helper(root.right, list);
        list.add(root.val);
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

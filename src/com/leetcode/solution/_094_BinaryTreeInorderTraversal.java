package com.leetcode.solution;

/**
 * 中序遍历
 * <p>
 * Time complexity : O(n). The time complexity is O(n) because the recursive function is T(n)=2⋅T(n/2)+1.
 * Space complexity : The worst case space required is O(n), and in the average case it's O(logn) where nn is number of nodes.
 */

public class _094_BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left != null)
                helper(root.left, res);
            res.add(root.val);
            if (root.right != null)
                helper(root.right, res);
        }
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

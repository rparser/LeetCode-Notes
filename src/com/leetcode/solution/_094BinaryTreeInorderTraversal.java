package com.leetcode.solution;

import java.util.*;

/**
 * 中序遍历
 *
 * Time complexity : O(n). The time complexity is O(n) because the recursive function is T(n)=2⋅T(n/2)+1.
 * Space complexity : The worst case space required is O(n), and in the average case it's O(logn) where nn is number of nodes.
 */

public class _094BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

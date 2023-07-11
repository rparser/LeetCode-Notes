package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 中序遍历
 * <p>
 * Time complexity : O(n). The time complexity is O(n) because the recursive function is T(n)=2⋅T(n/2)+1.
 * Space complexity : The worst case space required is O(n), and in the average case it's O(logn) where nn is number of nodes.
 */

public class _094_Binary_Tree_Inorder_Traversal {
    // O(N), O(N)
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        //处理curr或stack里的值
        while (curr != null || !stack.isEmpty()) {
            //先找到最左下的点,在此期间把每个路上的node放入stack
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            //此时curr是null
            curr = stack.pop();
            res.add(curr.val);
            //换为右子树处理
            curr = curr.right;
        }
        return res;
    }

    // O(n), space最坏O(n)，平均情况为O(logn)
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            //先找左边
            if (root.left != null)
                helper(root.left, res);
            //再加自己
            res.add(root.val);
            //再找右边
            if (root.right != null)
                helper(root.right, res);
        }
    }
}

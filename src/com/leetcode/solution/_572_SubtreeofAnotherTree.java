package com.leetcode.solution;

import java.util.HashSet;

/**
 * Time complexity: O(max(n, m))
 * Space complexity: O(max(n, m))
 */

public class _572_SubtreeofAnotherTree {
    private StringBuilder spre = new StringBuilder();
    private StringBuilder tpre = new StringBuilder();

    public boolean isSubtree(TreeNode s, TreeNode t) {
        preOrder(s, spre.append(","));
        preOrder(t, tpre.append(","));
        return spre.toString().contains(tpre.toString());
    }

    private void preOrder(TreeNode root, StringBuilder str) {
        if (root == null) {
            str.append("#,");
            return;
        }
        str.append(root.val).append(",");
        preOrder(root.left, str);
        preOrder(root.right, str);
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

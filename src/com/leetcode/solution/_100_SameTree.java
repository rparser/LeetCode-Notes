package com.leetcode.solution;

/**
 * 是否同树
 * 572子问题
 */

public class _100_SameTree {
    public boolean isSameTree(TreeNode s, TreeNode t) { //572子树问题
        if (s == null && t == null) return true; //两个空树返回true
        if (s == null || t == null) return false; //一个返回false

        if (s.val != t.val) return false; //数值不等返回false

        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right); //继续看子树
    }
}

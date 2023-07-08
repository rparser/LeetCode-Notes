package com.leetcode.solution;

/**
 * 是否为子树
 * 100的父题目+s空返F，st同反T，迭代看s子树
 * Time complexity: O(max(n, m))
 * Space complexity: O(max(n, m))
 */

public class _572_Subtree_of_Another_Tree {
    public boolean isSubtree(TreeNode s, TreeNode t) { //t是s的子树
        if (s == null) return false;
        if (isSame(s, t)) return true; //相等返回true
        return isSubtree(s.left, t) || isSubtree(s.right, t); //判断两个子树是否符合，直到s为空或同树
    }

    private boolean isSame(TreeNode s, TreeNode t) { //100问题，同一个树
        if (s == null && t == null) return true; //两个空树返回true
        if (s == null || t == null) return false; //一个返回false
        if (s.val != t.val) return false; //数值不等返回false

        return isSame(s.left, t.left) && isSame(s.right, t.right); //继续看子树
    }
}

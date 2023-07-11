package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

public class _173_Binary_Search_Tree_Iterator {
    private Deque<TreeNode> stack;

    public _173_Binary_Search_Tree_Iterator(TreeNode root) {
        stack = new LinkedList<>();// you can also inialize at var declaration
        push(root);//adding the node leftbranch to get the min
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode node = stack.pop();
        if (node.right != null) {
            // 这个push是自定义的函数在下面
            push(node.right);
        }
        return node.val;
    }

    /*adding the node with its left branch*/
    //一直找到最左下
    public void push(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}

package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

//全指向右侧
public class _114_Flatten_Binary_Tree_to_LinkedList {
    //递归 O(N), O(N)
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        //我们先假设已经完成，拥有了left,right两条链表该如何处理

        //将根节点的左子树变成链表
        flatten(root.left);
        //将根节点的右子树变成链表
        flatten(root.right);
        // root.right先存起来
        TreeNode temp = root.right;
        //把root.right换成left的链表
        root.right = root.left;
        //记得要将左边置空
        root.left = null;
        //找到树的最右边（下面）的节点
        while (root.right != null)
            root = root.right;
        //把tmp保存的真右边接到下面
        root.right = temp;
    }

    //前序迭代
    public void flattenIterative(TreeNode root) {
        if (root == null)
            return;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode pre = new TreeNode(-1);
        while (stack.size() > 0) {
            //用栈作为辅助数据结构，从栈中弹出元素后
            //先将右节点放到栈中，再将左节点放入栈中，模拟前序遍历
            TreeNode tmp = stack.pop();
            pre.left = null;
            pre.right = tmp;
            //先放入右节点，再放入左边点，顺序不能反了
            if (tmp.right != null)
                stack.push(tmp.right);

            if (tmp.left != null)
                stack.push(tmp.left);

            pre = pre.right;
        }
    }
}

package com.leetcode.solution;

public class _114_FlattenBinaryTreetoLinkedList {
    //递归
    public void flatten(TreeNode root) {
        if(root == null){
            return ;
        }
        //将根节点的左子树变成链表
        flatten(root.left);
        //将根节点的右子树变成链表
        flatten(root.right);
        TreeNode temp = root.right;
        //把树的右边换成左边的链表
        root.right = root.left;
        //记得要将左边置空
        root.left = null;
        //找到树的最右边的节点
        while(root.right != null) root = root.right;
        //把右边的链表接到刚才树的最右边的节点
        root.right = temp;
    }
//前序迭代
    public void flatten(TreeNode root) {
        if(root==null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);
        TreeNode pre = new TreeNode(-1);
        while(stack.size()>0) {
            //用栈作为辅助数据结构，从栈中弹出元素后
            //先将右节点放到栈中，再将左节点放入栈中，模拟前序遍历
            TreeNode tmp = stack.pop();
            pre.left = null;
            pre.right = tmp;
            //先放入右节点，再放入左边点，顺序不能反了
            if(tmp.right!=null) {
                stack.add(tmp.right);
            }
            if(tmp.left!=null) {
                stack.add(tmp.left);
            }
            pre = pre.right;
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

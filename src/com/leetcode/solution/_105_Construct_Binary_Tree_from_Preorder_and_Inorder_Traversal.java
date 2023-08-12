package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

class _105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    //没用重复元素 O(N), O(N)
    //level [1, 2,3,4, 5,6,7]的树
    //pre [1,2,4,5,3, 6, 7]
    //in [4,2,5,1, 6,3,7]
    public TreeNode buildTreeIterative(int[] preorder, int[] inorder) {
        Deque<TreeNode> roots = new LinkedList<>();
        int preIndex = 0;
        int inIndex = 0;
        //先序遍历第一个值是root
        TreeNode curRoot = new TreeNode(preorder[preIndex]);
        TreeNode root = curRoot;
        roots.push(curRoot);
        preIndex++;
        //遍历前序遍历的数组, 始终用preIndex
        while (preIndex < preorder.length) {
            // 如果curRoot不等inorder则作为左子树
            if (curRoot.val != inorder[inIndex]) {
                curRoot.left = new TreeNode(preorder[preIndex]);
                curRoot = curRoot.left;
            }
            //出现了curRoot和inorder遍历数组的值相等，寻找是谁的右子树
            else {
                //每次进行出栈，倒着遍历找到inorder里出现的但不在stack里的，即为右子树
                //因为右子树就在root后面！
                while (!roots.isEmpty() && roots.peek().val == inorder[inIndex]) {
                    curRoot = roots.peek();
                    roots.pop();
                    inIndex++;
                }
                //设为当前的右孩子(始终用preIndex)
                curRoot.right = new TreeNode(preorder[preIndex]);
                //更新 curRoot
                curRoot = curRoot.right;
            }
            roots.push(curRoot);
            preIndex++;
        }
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        //递归的第一步：递归终止条件，避免死循环
        if (pStart > pEnd || iStart > iEnd) {
            return null;
        }
        //重建根节点
        TreeNode treeNode = new TreeNode(preorder[pStart]);
        int index = 0;  // 找到根节点在inorder的位置index - index也是左子树的节点数
        while (inorder[iStart + index] != preorder[pStart]) {
            index++;
        }
        //重建左子树
        treeNode.left = dfs(preorder, pStart + 1, pStart + index, inorder, iStart, iStart + index - 1);
        //重建右子树
        treeNode.right = dfs(preorder, pStart + index + 1, pEnd, inorder, iStart + index + 1, iEnd);
        return treeNode;
    }
}
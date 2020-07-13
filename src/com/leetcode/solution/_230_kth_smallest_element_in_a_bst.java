package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class _230_kth_smallest_element_in_a_bst {
    //O(H+k), O(H+K)
    // Inorder BFS with counter 参考94
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        int counter = 0;
        int res = -1;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 不为空则一直左子树
            while (cur != null) {
                stack.push(cur);
                cur = cur.left; // 考虑左子树
            }
            // 出栈时计数
            cur = stack.pop();
            counter++;
            //counter到了跳出
            if (counter == k) {
                res = cur.val;
                break;
            }
            // 考虑右子树
            cur = cur.right;
        }
        return res;
    }

    //O(N),O(N)
    //递归 BST 的inOrder遍历序列，则第 k-1 个元素就是第 k 小的元素
    public int kthSmallestRecursive(TreeNode root, int k) {
        ArrayList<Integer> list = inorder(root, new ArrayList<>());
        return list.get(k - 1);
    }

    public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> list) {
        if (root == null) return list;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
        return list;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
package com.leetcode.solution;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class _230_Kth_Smallest_Element_in_a_BST {
    //O(H+k), O(H+K)
    // Inorder BFS with counter 参考94
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        int counter = 0;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 不为空则一直左子树
            while (cur != null) {
                stack.push(cur);
                cur = cur.left; // 先放左下因为左下最小
            }
            // 出栈时计数
            cur = stack.pop();
            counter++;
            // counter到了跳出
            if (counter == k) {
                return cur.val;
            }
            // 考虑右子树
            cur = cur.right;
        }
        return -1;
    }

    public int kthSmallest2(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty() && k > 0) {
            cur = stack.pop();
            if (--k == 0) return cur.val;
            cur = cur.right;
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }
        return -1;
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
}
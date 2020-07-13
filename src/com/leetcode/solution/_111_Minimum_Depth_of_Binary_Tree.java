package com.leetcode.solution;

import java.util.*;

/**
 * BFS遍历
 * Queue
 */

public class _111_Minimum_Depth_of_Binary_Tree {
    // O(N), O(N)
    public int minDepthIterative(TreeNode root) {
        if (root == null) return 0;
        int depth = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root); // offer添加, poll弹出, peek查询
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                // 找到叶子直接弹出
                if (node.left == null && node.right == null)
                    return depth;

                if (node.left != null)
                    q.offer(node.left);

                if (node.right != null)
                    q.offer(node.right);
            }
            depth++;
        }
        return depth;
    }

    // O(N), O(N)
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);
        //1.如果左孩子和右孩子有为空的情况，直接返回m1+m2+1 (其中有一个为0+不为零+1 root)
        //2.如果都不为空，返回较小深度+1 root
        return root.left == null || root.right == null ? m1 + m2 + 1 : Math.min(m1, m2) + 1;
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

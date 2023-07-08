package com.leetcode.solution;

import java.util.*;

public class _104_maximum_depth_of_binary_tree {
    // O(N) , O(N)
    public int maxDepthBFS(TreeNode root) {
        if (root == null) return 0;
        int depth = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null)
                    q.add(node.left);

                if (node.right != null)
                    q.add(node.right);
            }
            // 每层计数
            depth++;
        }
        return depth;
    }

    // O(N), call stack size O(logN) ~ O(H)最好，O(N)最差
    public int maxDepthDfs(TreeNode root) {
        if (root == null)
            return 0;
        int left = maxDepthDfs(root.left);
        int right = maxDepthDfs(root.right);
        // +1逻辑是：即使没有左右，计算过自身就有一层要+1
        return Math.max(left, right) + 1;
    }
}
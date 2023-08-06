package com.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

public class _104_Maximum_Depth_of_Binary_Free {
    // O(N), call stack size O(logN) ~ O(H)最好，O(N)最差
    int res = 0;
    public int maxDepth(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null)
            return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        res = Math.max(Math.max(res, left + 1), right + 1);
        // +1逻辑是：即使没有左右，计算过自身就有一层要+1
        return Math.max(left, right) + 1;
    }

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
}
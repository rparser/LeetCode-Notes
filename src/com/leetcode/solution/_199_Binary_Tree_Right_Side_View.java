package com.leetcode.solution;

import java.util.*;

/**
 * 思路1： BFS, use level order traversal. key is to track each level size, and add the last one to list.
 */

public class _199_Binary_Tree_Right_Side_View {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            // last element in current level
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.remove();
                if (i == size - 1)
                    result.add(curr.val);

                if (curr.left != null)
                    queue.offer(curr.left);

                if (curr.right != null)
                    queue.offer(curr.right);
            }
        }
        return result;
    }
    // 递归
    List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideViewRecursive(TreeNode root) {
        dfs(root, 0); // 从根节点开始访问，根节点深度是0
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null)
            return;

        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if (depth == res.size()) {   // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
            res.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
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

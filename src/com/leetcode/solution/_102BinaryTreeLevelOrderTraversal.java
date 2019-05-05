package com.leetcode.solution;

import java.util.*;

/**
 * 树分层遍历
 * <p>
 * Time complexity : O(N) since each node is processed exactly once.
 * Space complexity : O(N) to keep the output structure which contains N node values.
 */

public class _102BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(); //设定当前层有几个节点
            LinkedList<Integer> currLevel = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pop();
                currLevel.add(node.val); //把queue里小于size的结果加入当前层list
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(currLevel); //当前层list加入result
        }
        return result;
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

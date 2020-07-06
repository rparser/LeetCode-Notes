package com.leetcode.solution;

import java.util.*;

/**
 * zigzag分层遍历
 * 相比102，加入了一个判断，是否为leftToRight，每行更改一次，如果不是，则从currLevel右向左取，而且先加右节点再加左节点（加到头部）
 * <p>
 * Time complexity : O(N) since each node is processed exactly once.
 * Space complexity : O(N) to keep the output structure which contains N node values.
 * 模板while,for(每层)，取当前点，值加入结果，有子树加入队列，判断行数决定反向
 */

public class _103_BinaryTreeZigzagLevelOrderTraversal {
     // O(N) - O(N)
    public List<List<Integer>> zigzagLevelOrderbest(TreeNode root) { //BFS
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        int level = 0;//当前行
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            result.add(new ArrayList<>());
            for (int i = 0; i < size; i++) { //处理每一行
                TreeNode cur = q.poll();

                if (level % 2 == 0)
                    result.get(level).add(cur.val);
                else // 奇数则每次加到第一个（所以最右节点在第一个）
                    result.get(level).add(0, cur.val);

                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            level++;
        }
        return result;
    }

    // O(N) - O(H) height
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        List<List<Integer>> results = new ArrayList<>();
        DFS(root, 0, results);
        return results;
    }

    protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
        if (level >= results.size()) {
            LinkedList<Integer> newLevel = new LinkedList<>();
            newLevel.add(node.val);
            results.add(newLevel);
        } else {
            if (level % 2 == 0)
                results.get(level).add(node.val);
            else // 奇数则每次加到第一个（所以最右节点在第一个）
                results.get(level).add(0, node.val);
        }

        if (node.left != null)
            DFS(node.left, level + 1, results);
        if (node.right != null)
            DFS(node.right, level + 1, results);
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

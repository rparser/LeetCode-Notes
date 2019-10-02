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
                result.get(level).add(cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            if (level % 2 == 1) //第1行及每单数行要顺序反过来（root为0行）
                Collections.reverse(result.get(level));
            level++;
        }
        return result;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        LinkedList<TreeNode> list = new LinkedList<>();
        boolean leftToRight = true;
        list.add(root);
        while (!list.isEmpty()) {
            int size = list.size(); //设定当前层有几个节点
            LinkedList<Integer> currLevel = new LinkedList<>();
            if (leftToRight) { //判断本层是否是左到右
                for (int i = 0; i < size; i++) {
                    TreeNode node = list.remove(0);
                    currLevel.add(node.val); //把queue里小于size的结果加入当前层list
                    if (node.left != null) list.add(node.left); //先加左子再加右子
                    if (node.right != null) list.add(node.right);
                }
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode node = list.remove(list.size() - 1);
                    currLevel.add(node.val); //把queue里小于size的结果加入当前层list
                    if (node.right != null) list.add(0, node.right);//先加右子再加左子，且加到头部0
                    if (node.left != null) list.add(0, node.left);

                }
            }
            result.add(currLevel); //当前层list加入result
            leftToRight = !leftToRight;
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

package com.leetcode.solution;

import java.util.*;

/**
 * 树分层遍历
 * 一个Queue保存每层的节点，先计算当前Queue大小，然后依次pop出节点，再offer子节点
 * <p>
 * Time complexity : O(N) since each node is processed exactly once.
 * Space complexity : O(N) to keep the output structure which contains N node values.
 */

public class _102_Binary_Tree_Level_Order_Traversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(); //设定当前层有几个节点
            LinkedList<Integer> currLevel = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currLevel.add(node.val); //把queue里小于size的结果加入当前层list
                if (node.left != null)
                    queue.offer(node.left);

                if (node.right != null)
                    queue.offer(node.right);
            }
            result.add(currLevel); //当前层list加入result
        }
        return result;
    }

    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        if (root == null) return new ArrayList<>();
        //用来存放最终结果
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, root, res);
        return res;
    }

    void dfs(int index, TreeNode root, List<List<Integer>> res) {
        //假设res是[ [1],[2,3] ]， index是3，就再插入一个空list放到res中
        if (res.size() < index)
            res.add(new ArrayList<>());
        //将当前节点的值加入到res中，index代表当前层，假设index是3，节点值是99
        //res是[ [1],[2,3] [4] ]，加入后res就变为 [ [1],[2,3] [4,99] ]
        res.get(index - 1).add(root.val);
        //递归的处理左子树，右子树，同时将层数index+1
        if (root.left != null)
            dfs(index + 1, root.left, res);
        if (root.right != null)
            dfs(index + 1, root.right, res);
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

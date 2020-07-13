package com.leetcode.solution;

import java.util.*;

class _515_find_largest_value_in_each_tree_row {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null)
            return new LinkedList<>();
        //创建一个队列，用于存放当前节点相邻的节点
        Queue<TreeNode> queue = new LinkedList<>();
        //初始化，将根节点放入队列当中
        queue.add(root);
        LinkedList<Integer> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            //计算当前节点相邻的节点数量
            int size = queue.size();
            int min = Integer.MIN_VALUE;
            //遍历当前节点相邻节点
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                min = Math.max(min, temp.val);
                if (temp.left != null)
                    queue.add(temp.left);

                if (temp.right != null)
                    queue.add(temp.right);
            }
            result.add(min);
        }
        return result;
    }

    public List<Integer> largestValuesRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res, 1);
        return res;
    }

    //level表示的是第几层，集合res中的第一个数据表示的是
    // 第一层的最大值，第二个数据表示的是第二层的最大值……
    private void helper(TreeNode root, List<Integer> res, int level) {
        if (root == null) return;
        //如果走到下一层了直接加入到集合中
        if (level == res.size() + 1)
            res.add(root.val);
            // 所以这里level要减1因为集合list的下标是从0开始的
            // Math.max(res.get(level - 1), root.val)表示的
            // 是遍历到的第level层的root.val值和集合中的第level
            // 个元素的值哪个大，就要哪个。
        else
            res.set(level - 1, Math.max(res.get(level - 1), root.val));
        //下面两行是DFS的核心代码
        helper(root.left, res, level + 1);
        helper(root.right, res, level + 1);
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
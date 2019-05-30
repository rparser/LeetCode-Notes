package com.leetcode.solution;

import java.util.*;

/**
 * BFS解法,树的宽度
 * 队列存node:位置，for保证每层运行，左子树位置2r,右子树2r+1,max=r-l+1
 */
public class _662_MaximumWidthofBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int max = 0;
        Queue<Map.Entry<TreeNode, Integer>> q = new LinkedList<>(); //key存node,value-integer标记位置
        q.offer(new AbstractMap.SimpleEntry<>(root, 1));

        while (!q.isEmpty()) {
            int l = q.peek().getValue(), r = l; // 从同一个点开始,l为本层最左，r为本层最右
            for (int i = 0, n = q.size(); i < n; i++) { //记录每一层
                TreeNode node = q.peek().getKey();
                r = q.poll().getValue(); //得到位置
                if (node.left != null) q.offer(new AbstractMap.SimpleEntry<>(node.left, r * 2)); //左子树位置是2x
                if (node.right != null) q.offer(new AbstractMap.SimpleEntry<>(node.right, r * 2 + 1)); //右子树是2x+1
            }
            max = Math.max(max, r - l + 1);
        }
        return max;
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

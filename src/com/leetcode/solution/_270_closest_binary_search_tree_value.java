package com.leetcode.solution;

class _270_closest_binary_search_tree_value {
    //O(H), O(1)
    public int closestValue(TreeNode root, double target) {
        TreeNode cur = root;
        int curVal;
        int bestVal = root.val;

        while (cur != null) {
            curVal = cur.val;
            if (Math.abs(curVal - target) - Math.abs(bestVal - target) < 0)
                bestVal = curVal;

            // 目标值比当前根值大，搜右子树，反之左子树，如果没有找到就是保存的bestVal
            cur = target - curVal > 0 ? cur.right : cur.left;
        }
        return bestVal;
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
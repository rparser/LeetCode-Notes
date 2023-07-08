package com.leetcode.solution;

class _1026_maximum_difference_between_node_and_ancestor {
    int result = Integer.MIN_VALUE;
    // 使用全局变量保存最大差值，进行dfs，
    // 每次递归查找当前路径的最大值和最小值，到达叶子节点时使用当前路径的最大值和最小值更新全局变量。
    //O(N), O(N)
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;
        //如果当前节点没有子节点，则直接返回
        dfs(root, root.val, root.val);
        return result;
    }

    /**
     * 每条从根节点到叶子节点的路径中的最大值和最小值，并求出差值更新全局变量
     */
    private void dfs(TreeNode node, int max, int min) {
        if (node == null)
            return;
        max = Math.max(node.val, max);
        min = Math.min(node.val, min);

        //到达叶子节点，求最大差值
        if (node.left == null && node.right == null)
            result = Math.max(result, Math.abs(max - min));

        //不论是否叶子，继续dfs
        dfs(node.left, max, min);
        dfs(node.right, max, min);
    }
}

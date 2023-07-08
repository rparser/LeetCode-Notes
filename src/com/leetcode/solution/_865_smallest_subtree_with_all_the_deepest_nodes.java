package com.leetcode.solution;


class _865_smallest_subtree_with_all_the_deepest_nodes {
    //O(N), O(N)
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null)
            return null;

        int lCount = depth(root.left);
        int rCount = depth(root.right);
        //如果左右节点树高相等，那么该节点是最近公共节点，否则，要继续查找
        if (lCount == rCount)
            return root;
        else if (lCount > rCount)
            return subtreeWithAllDeepest(root.left);
        else
            return subtreeWithAllDeepest(root.right);
    }

    int depth(TreeNode root) {
        if (root == null)
            return 0;

        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}

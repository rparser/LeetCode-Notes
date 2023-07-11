package com.leetcode.solution;

class _129_sum_root_to_leaf_numbers {
    public int sumNumbers(TreeNode root) {
        return findRootToLeafPathNumbers(root, 0);
    }

    public int findRootToLeafPathNumbers(TreeNode currentNode, int pathSum) {
        if (currentNode == null)
            return 0;
        // calculate the path number of the current node
        pathSum = 10 * pathSum + currentNode.val;
        // if the current node is a leaf, return the current path sum.
        if (currentNode.left == null && currentNode.right == null)
            return pathSum;
        // traverse the left and the right sub-tree
        return findRootToLeafPathNumbers(currentNode.left, pathSum) +
                findRootToLeafPathNumbers(currentNode.right, pathSum);
    }
}

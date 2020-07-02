package com.leetcode.solution;

/**
 * 112.113,求树分支和为sum的个数
 * do two recursion. different from pathSumII that start can be any node, so we have 1st recur.
 * 1st： recursively travel tree to find answers of current node + ans of left child + ans of right child.
 * 2nd: find the path from current node to child that adds up to sum
 * Complexity:
 * If the tree is balanced, then each node is reached from its ancestors (+ itself) only, which are up to log n. Thus, the time complexity for a balanced tree is O (n * log n).
 * However, in the worst-case scenario where the binary tree has the same structure as a linked list, the time complexity is indeed O (n ^ 2) best O(N*logN)
 * O(N) space - height of binary tree best O(logN)
 * <p>
 * DFS，当前分支和为root+左+右，dfs函数，sum-root.val==0, count++，继续算count+=左右递归
 */

public class _437_PathSumIII {
    public int dfs(TreeNode root, int sum) {
        int count = 0;
        if (root == null)
            return 0;
        if (sum - root.val == 0)
            count++;

        count += dfs(root.left, sum - root.val);
        count += dfs(root.right, sum - root.val);
        return count;
    }

    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;

        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
        //so total count will be countOf(hasRoot) + countOf(leftChild) + countOf(rightChild)
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

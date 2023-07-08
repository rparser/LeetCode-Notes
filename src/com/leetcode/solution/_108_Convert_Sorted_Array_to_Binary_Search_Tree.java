package com.leetcode.solution;

import java.util.*;

// O(N),O(logN)递归栈的深度
class _108_Convert_Sorted_Array_to_Binary_Search_Tree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int lo, int hi) {
        if (lo > hi)
            return null;

        // 以升序数组的中间元素作为根节点 root。
        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        // 递归的构建 root 的左子树与右子树。
        root.left = dfs(nums, lo, mid - 1);
        root.right = dfs(nums, mid + 1, hi);
        return root;
    }
// [1,2,3,4,5,6,7,8,9] 返回[5,2,7,1,3,6,8,null,null,null,4,null,null,null,9]
    public TreeNode sortedArrayToBST_Iterative(int[] num) {
        if (num.length == 0)
            return null;
        Queue<int[]> rangeQueue = new LinkedList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        int lo = 0;
        int hi = num.length - 1;
        int mid = lo + (hi - lo) / 2;
        // 根节点
        TreeNode root = new TreeNode(num[mid]);
        // range是对应nodeQueue里node的children index范围（两个queue永远同大小）
        rangeQueue.add(new int[]{lo, mid - 1});
        rangeQueue.add(new int[]{mid + 1, hi});
        nodeQueue.add(root);
        nodeQueue.add(root);
        while (!rangeQueue.isEmpty()) {
            int[] range = rangeQueue.poll();
            TreeNode currentNode = nodeQueue.poll();
            lo = range[0];
            hi = range[1];
            if (lo > hi)
                continue;
            // 每一次都把range中值取出当左右子node，再进入下一层
            mid = lo + (hi - lo) / 2;
            int midValue = num[mid];
            TreeNode newNode = new TreeNode(midValue);
            if (midValue > currentNode.val)
                currentNode.right = newNode;
            else
                currentNode.left = newNode;

            if (lo < hi) {
                rangeQueue.add(new int[]{lo, mid - 1});
                rangeQueue.add(new int[]{mid + 1, hi});
                nodeQueue.add(newNode);
                nodeQueue.add(newNode);
            }
        }
        return root;
    }
}
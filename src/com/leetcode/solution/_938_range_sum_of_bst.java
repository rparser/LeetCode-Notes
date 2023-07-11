package com.leetcode.solution;

//后序遍历（深度优先）+减枝算法

import java.util.Stack;

class _938_range_sum_of_bst {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        int res = 0;
        //减枝条件
        if (root.val > L)
            res += rangeSumBST(root.left, L, R);
        if (root.val < R)
            res += rangeSumBST(root.right, L, R);

        if (root.val >= L && root.val <= R)
            res += root.val;

        return res;
    }

    public int rangeSumBSTIT(TreeNode root, int L, int R) {
        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                //如果在之间加入结果
                if (L <= node.val && node.val <= R)
                    ans += node.val;
                //如果node > L，加左子树进stack
                if (L < node.val)
                    stack.push(node.left);
                //如果node < R 加入右子树进stack
                if (node.val < R)
                    stack.push(node.right);
            }
        }
        return ans;
    }
}
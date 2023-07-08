package com.leetcode.solution;

class _285_inorder_successor_in_bst {
    // 没有p也会返回null O(logN) ~ O(H), O(1)
    public TreeNode inorderSuccessorIterative(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;

        TreeNode result = null;
        // 找到此值p之后，向以p为root的右子树查找，如果没有右子树，则返回上一个保存的result
        // 如果有右子树，则一直更新result直到右子树里的最左值。
        while (root != null) {
            // 要去右子树查找
            if (p.val >= root.val)
                root = root.right;
                // 更新最左值（即使没有左子树，也可以更新result = root为下一个值）
            else {
                result = root;
                root = root.left;
            }
        }
        return result;
    }

    // O(logN) ~ O(H), O(logN) ~ O(H)
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p == null || root == null)
            return null;
        //如果p大于root，就去右子树找
        if (p.val >= root.val)
            return inorderSuccessor(root.right, p);

        //现在是p 比 root小，则要去左子树，最左下找result，在这里返回
        TreeNode leftMost = inorderSuccessor(root.left, p);
        // 一层一层把左子树返回（如果有左子树的话）
        if (leftMost != null)
            return leftMost;
            // 直到没有找到左child,返回的是root
        else
            return root;
    }
}
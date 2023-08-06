package com.leetcode.solution;

/**
 * 最近公共祖先BST
 * 如果p和q在root的不同侧，
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the BST.
 * Space Complexity: O(N). This is because the maximum amount of space utilized by the recursion stack would be N since the height of a skewed BST could be N.
 */

public class _235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree {
    // O(N), O(1)
    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            // 如果root同时大于p,q去左子树找
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
                continue;
            }
            // 如果root同时小于p,q去右子树找
            if (root.val < p.val && root.val < q.val) {
                root = root.right;
                continue;
            }
            //分叉即跳出
            break;
        }
        return root;
    }

    //递归 // O(N), O(N) size of call stack
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int parentVal = root.val; // Value of current node or parent node.
        int pVal = p.val;
        int qVal = q.val;

        if (pVal > parentVal && qVal > parentVal)  //p,q都大于parent则从parent右子树找
            return lowestCommonAncestor(root.right, p, q);
        else if (pVal < parentVal && qVal < parentVal) //都小于则从左子树
            return lowestCommonAncestor(root.left, p, q);
        else
            return root; //在pq之间则返回parent
    }

    // 属于二叉搜索树的递归
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //如果小于等于0，说明p和q位于root的两侧，直接返回即可
        if ((root.val - p.val) * (root.val - q.val) <= 0)
            return root;
        //否则，p和q位于root的同一侧，就继续往下找
        return lowestCommonAncestor(p.val < root.val ? root.left : root.right, p, q);
    }

    // 下面这个236非BST代码也能解决235
    public TreeNode lowestCommonAncestor236(TreeNode cur, TreeNode p, TreeNode q) {
        if (cur == null || cur == p || cur == q)
            return cur;
        TreeNode left = lowestCommonAncestor(cur.left, p, q);
        TreeNode right = lowestCommonAncestor(cur.right, p, q);
        //如果left为空，说明这两个节点在cur结点的右子树上，我们只需要返回右子树查找的结果即可
        if (left == null)
            return right;
        //同上
        if (right == null)
            return left;
        //如果left和right都不为空，说明这两个节点一个在cur的左子树上一个在cur的右子树上，
        //我们只需要返回cur结点即可。
        return cur;
    }
}

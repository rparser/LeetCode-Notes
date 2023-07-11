package com.leetcode.solution;

/**
 * 最近公共祖先BST
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the BST. In the worst case we might be visiting all the nodes of the BST.
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
}

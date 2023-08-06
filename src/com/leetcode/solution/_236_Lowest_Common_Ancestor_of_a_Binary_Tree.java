package com.leetcode.solution;

import java.util.*;

/**
 * 最近公共祖先普通
 * <p>
 * Time Complexity : O(N), where NN is the number of nodes in the binary tree.
 * In the worst case we might be visiting all the nodes of the binary tree.
 * <p>
 * Space Complexity : O(N). In the worst case space utilized by the stack, the parent pointer dictionary and the ancestor set,
 * would be N each, since the height of a skewed binary tree could be NN.
 * <p>
 * 建queue BFS,map(node : parent of node)，while到pq都进入map,建set while存p所有祖先，while q祖先找第一出现的
 */

public class _236_Lowest_Common_Ancestor_of_a_Binary_Tree {
    //递归 可以是自己的祖先
    public TreeNode lowestCommonAncestor(TreeNode cur, TreeNode p, TreeNode q) {
        if (cur == null || cur == p || cur == q)
            return cur;
        TreeNode left = lowestCommonAncestor(cur.left, p, q);
        TreeNode right = lowestCommonAncestor(cur.right, p, q);
        //如果left为空，说明这两个节点在cur结点的右子树上，我们只需要返回右子树查找的结果即可
        if (left == null) {
            return right;
        }
        //同上
        if (right == null) {
            return left;
        }
        //如果left和right都不为空，说明这两个节点一个在cur的左子树上一个在cur的右子树上，
        //我们只需要返回cur结点即可。
        return cur;
    }

    //迭代
    public TreeNode lowestCommonAncestorIteative(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>(); //队列存结果
        //<key : value - node, node的父节点>
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        queue.offer(root);

        //直到p,q的都压入
        while (!(parent.containsKey(p) && !parent.containsKey(q))) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                parent.put(node.left, node);
                queue.offer(node.left);
            }

            if (node.right != null) {
                parent.put(node.right, node);
                queue.offer(node.right);
            }
        }

        Set<TreeNode> ancestors = new HashSet<>(); //存祖先节点

        while (p != null) { //先把p祖先节点都加入
            ancestors.add(p);
            p = parent.get(p);
        }
        while (!ancestors.contains(q)) //q再往上找，直到出现的第一个即为LCA
            q = parent.get(q);

        return q;
    }
}

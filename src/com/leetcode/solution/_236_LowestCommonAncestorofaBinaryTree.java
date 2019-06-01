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

public class _236_LowestCommonAncestorofaBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> stack = new LinkedList<>(); //队列存结果
        Map<TreeNode, TreeNode> parent = new HashMap<>(); //key : value - node, node的父节点

        parent.put(root, null);
        stack.offer(root);

        while (!parent.containsKey(p) || !parent.containsKey(q)) { //直到p,q的都压入

            TreeNode node = stack.poll();

            if (node.left != null) {
                parent.put(node.left, node);
                stack.offer(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.offer(node.right);
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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
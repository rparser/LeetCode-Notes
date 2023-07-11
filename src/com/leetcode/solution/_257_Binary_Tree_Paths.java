package com.leetcode.solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class _257_Binary_Tree_Paths {
    //O(N), O(N)
    private final List<String> result = new ArrayList<>();
    private final StringBuilder sb = new StringBuilder();

    public List<String> binaryTreePathsRecursive(TreeNode root) {
        //自己试一下
        dfs(result, sb, root);
        return result;
    }

    public void dfs(List<String> list, StringBuilder sb, TreeNode node) {
        if (node == null) return;
        int len = sb.length(); //保存当前长度
        sb.append(node.val);

        if (node.left == null && node.right == null)
            list.add(sb.toString());

        else {
            sb.append("->");
            dfs(list, sb, node.left);
            dfs(list, sb, node.right);
        }
        sb.setLength(len); //恢复长度

        // When using StringBuilder, We can just keep track of the
        // length of the StringBuilder before we append anything to it before recursion
        // and afterwards set the length back. Another trick is when to append the "->",
        // since we don't need the last arrow at the end of the string,
        // we only append it before recurse to the next level of the tree.
    }

    // Iterative
    public List<String> binaryTreePaths(TreeNode root) {
        LinkedList<String> paths = new LinkedList<>();
        if (root == null)
            return paths;

        LinkedList<TreeNode> node_stack = new LinkedList<>();
        LinkedList<String> path_stack = new LinkedList<>();
        node_stack.add(root);
        path_stack.add(Integer.toString(root.val));
        TreeNode node;
        String path;
        while (!node_stack.isEmpty()) {
            node = node_stack.pollLast();
            path = path_stack.pollLast();
            if ((node.left == null) && (node.right == null))
                paths.add(path);

            if (node.left != null) {
                node_stack.add(node.left);
                path_stack.add(path + "->" + Integer.toString(node.left.val));
            }

            if (node.right != null) {
                node_stack.add(node.right);
                path_stack.add(path + "->" + Integer.toString(node.right.val));
            }
        }
        return paths;
    }
}
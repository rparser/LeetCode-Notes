package com.leetcode.solution;

import java.util.*;

/**
 * serialized size: 4*n bytes, n is the number of nodes in the BST.
 * Time complexity: O(n)
 * Space complexity: O(n)
 * queue.addAll(Arrays.asList(data.split(SPLITOR))); //直接将可分割的String转换成List
 * TreeNode root = new TreeNode(Integer.valueOf(p)); //将String类型变量转换成Integer
 * 序列化：前序遍历二叉树，遇到null节点以字符串“X”替代，每个节点间用”,”分割。
 * 反序列化：根据前序遍历序列递归新建二叉树。
 */

public class _449_Serialize_and_Deserialize_BST {
    private static final String NULL = "X";
    private static final String SPLITOR = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorderWalk(root, sb);
        return sb.toString();
    }

    private void preorderWalk(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SPLITOR);
            return;
        }
        sb.append(root.val).append(SPLITOR);
        preorderWalk(root.left, sb);
        preorderWalk(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(SPLITOR)));
        return build(queue);
    }

    private TreeNode build(Queue<String> queue) {
        String p = queue.poll();
        if (p.equals(NULL))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(p));
        root.left = build(queue);
        root.right = build(queue);
        return root;
    }
}

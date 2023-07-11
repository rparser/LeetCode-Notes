package com.leetcode.solution;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 思路： use 2 string marker: "#" for null - empty node, "," for splitter - splitting nodes
 * To Serialize, do pre-order traversal. Append node.val(sb可直接append数字) + splitter
 * to StringBuilder recursively.
 * To Deserialize, create a queue to add nodes from string(need to 1st split 2nd convert to arraylist) .
 * Then recursively build the tree by pointing left & right children.
 * Complexity: O(N) time travel all nodes
 */

public class _297_Serialize_and_Deserialize_Binary_Tree {
    //递归前序遍历per-order O(N), O(N) [1,2,4,5,3,6,7]
    private final String NULL = "#";
    private final String SPLITTER = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL).append(SPLITTER);
            return;
        }

        sb.append(node.val).append(SPLITTER);
        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    // Decodes your encoded data to tree.
    //将此时数据二叉树的先序遍历结果依次压入队列
    public TreeNode deserialize(String toProcess) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(toProcess.split(SPLITTER))); //不要忘Arrays.asList
        return buildTree(nodes);
    }

    private TreeNode buildTree(Queue<String> nodes) {
        String val = nodes.remove();//return null
        if (val.equals(NULL))
            return null;
        //若节点值不为空，将其由String转换回int
        //将其作为当前节点值新建当前节点
        TreeNode node = new TreeNode(Integer.parseInt(val));//Integer.parseInt()
        //递归preorder,处理完左子树再处理右子树 - 下面这两步不要搞错 参数是queue
        node.left = buildTree(nodes);
        node.right = buildTree(nodes);
        return node;
    }
}

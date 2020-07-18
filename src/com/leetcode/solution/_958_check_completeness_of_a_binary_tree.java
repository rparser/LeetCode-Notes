package com.leetcode.solution;

import java.util.*;

class _958_check_completeness_of_a_binary_tree {
    // O(N), O(N) 层序遍历,分别加入
    // incomplete的标准是，层序遍历，null被间隔开来
    public boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        TreeNode cur;
        queue.add(root);
        // 从头remove，到第一个不满的值停止,否则加入左右子树（可能为null）
        while ((cur = queue.remove()) != null) {
            queue.add(cur.left);
            queue.add(cur.right);
            //会在最后加入null
        }
        //当出现cur为null时，后面的值应该都为null，否则就不是complete
        while (!queue.isEmpty())
            // return false;
            if (queue.remove() != null)
                return false;

        return true;
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
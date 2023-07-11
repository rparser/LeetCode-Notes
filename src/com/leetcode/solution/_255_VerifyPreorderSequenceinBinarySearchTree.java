package com.leetcode.solution;

import java.util.Stack;

/**
 * 参考144
 */

public class _255_VerifyPreorderSequenceinBinarySearchTree {
    public boolean verifyPreorder(int[] preorder) {
        int size = 0;
        int min = Integer.MIN_VALUE;
        for (int node : preorder) {
            if (node < min) return false; // 到右子树还要小
            while (size > 0 && preorder[size - 1] < node)
                min = preorder[--size];
            preorder[size++] = node;
        }
        return true;
    }

    public boolean verifyPreorderStack(int[] preorder) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> inorder = new Stack<>();

        for (int node : preorder) {
            if (!inorder.isEmpty() && node < inorder.peek()) return false; // 新值不能比排序后的小
            while (!stack.isEmpty() && node > stack.peek()) // stack有值且新值大于peek，则要清空stack加入inorder
                inorder.push(stack.pop());
            stack.push(node);
        }
        return true;
    }
}

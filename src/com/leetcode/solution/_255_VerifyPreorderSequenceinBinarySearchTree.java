package com.leetcode.solution;

import java.util.Stack;

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
}

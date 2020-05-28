package com.leetcode.solution;

import java.util.*;

public class _085_MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int rLen = matrix.length, cLen = rLen == 0 ? 0 : matrix[0].length, max = 0;
        int[] h = new int[cLen + 1];

        for (int row = 0; row < rLen; row++) {
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(-1); //设置底部

            for (int i = 0; i <= cLen; i++) {
                if (i < cLen && matrix[row][i] == '1') h[i] += 1;
                else h[i] = 0;

                while (stack.peek() != -1 && h[i] < h[stack.peek()]) //如果没到底部
                    max = Math.max(max, h[stack.pop()] * (i - stack.peek() - 1));
                stack.push(i);
            }
        }
        return max;
    }
}

package com.leetcode.solution;

import java.util.*;

/**
 * 向上的行是u，向下的行是d，向左的列是l，向右的列是r。
 * k是spiral的当前索引，不断往后走。
 * 控制结束的是这4个方向的索引的大小比较。
 * Time Complexity: O(N), where N is the total number of elements in the input matrix. We add every element in the matrix to our final answer.
 * Space Complexity: O(N), the information stored in ans.
 */

public class _054_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiral = new ArrayList<>();
        if (matrix.length < 1) return spiral;
        int m = matrix.length, n = matrix[0].length;
        int u = 0, d = m - 1, l = 0, r = n - 1, k = 0;
        while (true) {
            // 空一行则+2
            // up
            for (int col = l; col <= r; col++) spiral.add(k++, matrix[u][col]);
            if (++u > d) break;
            // right
            for (int row = u; row <= d; row++) spiral.add(k++, matrix[row][r]);
            if (--r < l) break;
            // down
            for (int col = r; col >= l; col--) spiral.add(k++, matrix[d][col]);
            if (--d < u) break;
            // left
            for (int row = d; row >= u; row--) spiral.add(k++, matrix[row][l]);
            if (++l > r) break;
        }
        return spiral;
    }
}

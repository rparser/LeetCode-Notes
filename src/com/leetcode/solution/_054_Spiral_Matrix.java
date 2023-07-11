package com.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * 向上的行是u，向下的行是d，向左的列是l，向右的列是r。
 * k是spiral的当前索引，不断往后走。
 * 控制结束的是这4个方向的索引的大小比较。
 * Time Complexity: O(N), where N is the total number of elements in the input matrix. We add every element in the matrix to our final answer.
 * Space Complexity: O(N), the information stored in ans.
 */

public class _054_Spiral_Matrix {
    // O(N), O(N)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiral = new ArrayList<>();
        if (matrix.length < 1) return spiral;
        int m = matrix.length, n = matrix[0].length;
        int u = 0, d = m - 1, l = 0, r = n - 1;
        while (true) {
            // up
            for (int col = l; col <= r; col++)
                spiral.add(matrix[u][col]);
            //当有任何一个不符合时,即需要跳出循环
            // ++在前是为了比如3行4列，当时就要跳出否则还会再逆向算一次
            if (++u > d)
                break;
            // right
            for (int row = u; row <= d; row++)
                spiral.add(matrix[row][r]);
            if (--r < l)
                break;
            // down
            for (int col = r; col >= l; col--)
                spiral.add(matrix[d][col]);
            if (--d < u)
                break;
            // left
            for (int row = d; row >= u; row--)
                spiral.add(matrix[row][l]);
            if (++l > r)
                break;
        }
        return spiral;
    }
}

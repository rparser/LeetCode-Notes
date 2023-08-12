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
        List<Integer> result = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        int u = 0, d = m - 1, l = 0, r = n - 1; // up第一行 - down最后一行 - left第一列 -right最后一列
        while (true) {
            // up - 上面左到右
            for (int col = l; col <= r; col++) {
                result.add(matrix[u][col]);
            }
            // 当有任何一个不符合时,即需要跳出循环 - 因为已经到底
            // ++在前是因为u和d最差也要是同一行，所以先++，如果超出了立刻跳出
            // 先验算ud再使用ud计算
            if (++u > d) {
                break;
            }
            // right - 右侧上到下
            for (int row = u; row <= d; row++) {
                result.add(matrix[row][r]);
            }
            if (--r < l) {
                break;
            }
            // down - 下面右到左
            for (int col = r; col >= l; col--) {
                result.add(matrix[d][col]);
            }
            if (--d < u) {
                break;
            }
            // left - 左侧下到上
            for (int row = d; row >= u; row--) {
                result.add(matrix[row][l]);
            }
            if (++l > r) {
                break;
            }
        }
        return result;
    }
}

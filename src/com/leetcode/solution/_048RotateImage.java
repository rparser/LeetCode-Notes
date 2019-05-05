package com.leetcode.solution;

/**
 * 矩阵转动
 * <p>
 * Time complexity : O(N^2) is a complexity given by two inserted loops.
 * Space complexity : O(1) since we do a rotation in place.
 */

public class _048RotateImage {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int top = 0;
        int left = 0;
        int right = matrix.length - 1;
        int bottom = matrix.length - 1;
        int n = matrix.length;
        while (n > 1) { //必须大于1，中间值不转
            for (int i = 0; i < n - 1; i++) { //0是四个顶点，到n-1是顶点前一个点
                int temp = matrix[top][left + i];
                matrix[top][left + i] = matrix[bottom - i][left]; //一个四个边的对应
                matrix[bottom - i][left] = matrix[bottom][right - i];
                matrix[bottom][right - i] = matrix[top + i][right];
                matrix[top + i][right] = temp;
            }
            top++; //各自往里缩一行/列
            bottom--;
            left++;
            right--;
            n -= 2; //每次少两行/列
        }
    }
}

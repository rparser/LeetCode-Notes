package com.leetcode.solution;

class _073_set_matrix_zeroes {
    //O(M×N), O(1)
    // 记录第一行/列是否有0，储存，把第一行列作为标志位记录，然后置零，再还原第一行/列
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean row0_flag = false;
        boolean col0_flag = false;
        // 第一行是否有零
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                row0_flag = true;
                break;
            }
        }
        // 第一列是否有零
        for (int[] ints : matrix) {
            if (ints[0] == 0) {
                col0_flag = true;
                break;
            }
        }
        // 把第一行第一列作为标志位
        for (int i = 1; i < row; i++)
            for (int j = 1; j < col; j++)
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
        // 置0
        for (int i = 1; i < row; i++)
            for (int j = 1; j < col; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
        // 还原第一行
        if (row0_flag)
            for (int j = 0; j < col; j++)
                matrix[0][j] = 0;
        // 还原第一列
        if (col0_flag)
            for (int i = 0; i < row; i++)
                matrix[i][0] = 0;
    }
}

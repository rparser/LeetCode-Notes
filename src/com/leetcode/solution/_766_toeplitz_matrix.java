package com.leetcode.solution;

class _766_toeplitz_matrix {
    // O(M*N), O(1) 检查左上邻居
    public boolean isToeplitzMatrix(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length ==0)
            return true;
        // 所以从1,1开始,检查左上邻居
        for (int r = 1; r < matrix.length; ++r)
            for (int c = 1; c < matrix[0].length; ++c)
                if (matrix[r - 1][c - 1] != matrix[r][c])
                    return false;

        return true;
    }
}
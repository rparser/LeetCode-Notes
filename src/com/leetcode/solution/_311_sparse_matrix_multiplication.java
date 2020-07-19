package com.leetcode.solution;

class _311_sparse_matrix_multiplication {
    // O(MNK), O(MNK)
    public int[][] multiply(int[][] A, int[][] B) {
        // A的行 + B的列
        int rows = A.length;
        int cols = B[0].length;
        // time是每一次是B的行数(A的列)，每一次需要计算time次
        int times = B.length;
        int[][] res = new int[rows][cols];
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                // 要得到这么多个元素
                for (int i = 0; i < times; i++)
                    res[row][col] += A[row][i] * B[i][col];

        return res;
    }
}

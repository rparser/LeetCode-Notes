package com.leetcode.solution;

//选左上角，往右走和往下走都增大，不能选
//选右下角，往上走和往左走都减小，不能选
//选左下角，往右走增大，往上走减小，可选
//选右上角，往下走增大，往左走减小，可选

class _240_search_a_2d_matrix_ii {
    // O(m+n), O(1) 线性复杂度 从左下开始（或右上）
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int col = 0;
        //左下，Target小于当前值，则Target必然在当前值的左上，左下或右上（不可能右下）
        //但由于左下没有值了，所以必然在左上或右上，即需要向上移动
        //同理，Target大于当前值向右移动
        while (row >= 0 && col < matrix[0].length)
            if (target < matrix[row][col] )
                row--;
            else if (target > matrix[row][col])
                col++;
            else  // found it
                return true;

        return false;
    }
}
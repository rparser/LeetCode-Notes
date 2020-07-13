package com.leetcode.solution;

class _074_search_a_2d_matrix {
    //O(log(mn)), O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int row = matrix.length;
        int col = matrix[0].length;

        // 2D变1D，right就是最后一个元素。
        int left = 0, right = row * col - 1;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            // 重点：借助行数，将一维的索引值转换回二维坐标 行：mid / col, 列：mid % col。
            // 如果这一点比target小，那么mid和mid左边的值都不可能是解
            if (matrix[mid / col][mid % col] < target)
                // 将区间转换为[mid + 1, right]
                left = mid + 1;
            else
                right = mid;
        }
        //判断最后结束位置是不是target，如果是则true，不是则false。
        return matrix[left / col][left % col] == target;
    }
}

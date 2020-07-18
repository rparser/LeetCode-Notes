package com.leetcode.solution;

class _498_diagonal_traverse {
    //O(N), O(1)
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null) return null;
        if (matrix.length == 0) return new int[]{};
        if (matrix.length == 1) return matrix[0];

        int size = matrix.length * matrix[0].length;
        int[] result = new int[size];
        int row = 0;
        int col = 0;
        //[1,2,3,4,5,6,7,8,9] -> [1,2,4,7,5,3,6,8,9]
        //true代表向右上角遍历，false代表向左下角遍历
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            result[i] = matrix[row][col];
            if (flag) {
                // 向右上row-- col++
                row--;
                col++;
                //col过大，3到6
                if (col > matrix[0].length - 1) {
                    col = matrix[0].length - 1;
                    row += 2;
                    flag = false;
                }
                //row过小，1到2
                if (row < 0) {
                    row = 0;
                    flag = false;
                }
            } else {
                //向左下遍历，row++,col--
                row++;
                col--;
                //row过大，8到9
                if (row > matrix.length - 1) {
                    row = matrix.length - 1;
                    col += 2;
                    flag = true;
                }
                //col过小,4到7
                if (col < 0) {
                    col = 0;
                    flag = true;
                }
            }
        }
        return result;
    }
}

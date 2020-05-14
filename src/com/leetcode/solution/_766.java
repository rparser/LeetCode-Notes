class Solution {
    bool isToeplitzMatrix(int**matrix, int matrixSize, int*matrixColSize) {
        if (matrixSize <= 1 || *matrixColSize <= 1){
            return true;
        }
        // 第一个行元素遍历
        for (int i = 0; i < *matrixColSize - 1;
        i++){
            int n = 0;
            int m = i;
            while (m < *matrixColSize - 1 && n < matrixSize - 1){
                if (matrix[n][m] != matrix[n + 1][m + 1]) {
                    return false;
                }
                m++;
                n++;
            }
        }
        // 第一列元素遍历
        for (int i = 1; i < matrixSize - 1; i++) {
            int n = i;
            int m = 0;
            while (m < *matrixColSize - 1 && n < matrixSize - 1){
                if (matrix[n][m] != matrix[n + 1][m + 1]) {
                    return false;
                }
                m++;
                n++;
            }
        }
        return true;
    }
}
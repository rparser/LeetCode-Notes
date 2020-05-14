//dp[row][col] = dp[row][col-1] + dp[row-1][col] - dp[row-1][col-1] + matrix[row][col]
class NumMatrix {
    int[][] matrix;
    int[][] dp;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        if (matrix != null && matrix.length != 0) {
            dp = new int[matrix.length][matrix[0].length];
            initialDp(matrix, dp);
        }
    }

    private void initialDp(int[][] matrix, int[][] dp) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = matrix[i][j];
                } else if (i == 0) {
                    dp[i][j] = matrix[i][j] + dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = matrix[i][j] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (dp == null || dp.length == 0) {
            return 0;
        }
        //左边部分
        int left = 0;
        //上边部分
        int top = 0;
        //左上部分
        int leftTop = 0;
        if (col1 != 0) {
            left = dp[row2][col1 - 1];
        }
        if (row1 != 0) {
            top = dp[row1 - 1][col2];
        }

        if (col1 != 0 && row1 != 0) {
            leftTop = dp[row1 - 1][col1 - 1];
        }
        return dp[row2][col2] - left - top + leftTop;
    }
}
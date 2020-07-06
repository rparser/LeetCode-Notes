//dp[row][col] = dp[row][col-1] + dp[row-1][col] - dp[row-1][col-1] + matrix[row][col]
class NumMatrix {
    private int[][] dp;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return;
        // 设置多一个的理由：计算到[1,1]（第四个点），需要前面-1行的点总结出公式，但如果第0行-1就变负数
        // 所以需要第一行第一列都为0才方便总结公式
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; r++)
            for (int c = 0; c < matrix[0].length; c++)
                dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}
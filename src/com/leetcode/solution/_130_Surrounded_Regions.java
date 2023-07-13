package com.leetcode.solution;

class _130_Surrounded_Regions {
    private final int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int m, n;

    private void dfs(char[][] board, int x, int y) {
        // 若 O 在边界或和边界的 O 相连，则都换成 P
        board[x][y] = 'P';
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && board[newX][newY] == 'O') {
                dfs(board, newX, newY);
            }
        }
        return;
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        m = board.length;
        n = board[0].length;

        // 将边界位置的 O 都换成 P
        for (int i = 0; i < m; i++) {
            // 遍历第一列
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            // 遍历最后一列
            if (board[i][n - 1] == 'O') {
                dfs(board, i, n - 1);
            }
        }

        // 将边界位置的 O 都换成 P
        for (int j = 0; j < n; j++) {
            // 遍历第一行
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }
            // 遍历最后一行
            if (board[m - 1][j] == 'O') {
                dfs(board, m - 1, j);
            }
        }

        // 最后遍历完所有边界元素后，将元素复位
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'P') {
                    board[i][j] = 'O';
                }
            }
        }
    }
}
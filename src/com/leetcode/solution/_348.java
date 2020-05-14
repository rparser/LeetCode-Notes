class TicTacToe {
    int[][] arr;
    int n;

    public TicTacToe(int n) {
        this.arr = new int[n][n];
        this.n = n;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        arr[row][col] = player;
        // 行满足要求
        for (int i = 0; i < n; i++) {
            if (arr[row][i] == player) {
                if (i == n - 1) {
                    return player;
                }
            } else {
                break;
            }
        }
        // 列满足要求
        for (int i = 0; i < n; i++) {
            if (arr[i][col] == player) {
                if (i == n - 1) {
                    return player;
                }
            } else {
                break;
            }
        }
        // 左下满足要求
        if (row == col) {
            for (int i = 0; i < n; i++) {
                if (arr[i][i] == player) {
                    if (i == n - 1) {
                        return player;
                    }
                } else {
                    break;
                }
            }
        }
        // 左上满足要求
        if (row + col == n - 1) {
            for (int i = 0; i < n; i++) {
                if (arr[n - i - 1][i] == player) {
                    if (i == n - 1) {
                        return player;
                    }
                } else {
                    break;
                }
            }
        }
        return 0;
    }
}

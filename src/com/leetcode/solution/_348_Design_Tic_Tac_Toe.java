package com.leetcode.solution;

class _348_Design_Tic_Tac_Toe {
    int[][] arr;
    int n;

    public _348_Design_Tic_Tac_Toe(int n) {
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

        for (int i = 0; i < n; i++) {
            //行不同则直接break
            if (arr[row][i] != player) {
                break;
            }
            // 到最后一个还没break
            else if (i == n - 1) {
                return player;
            }
        }

        for (int i = 0; i < n; i++) {
            // 列不同则直接break
            if (arr[i][col] != player) {
                break;
            } else if (i == n - 1) {
                return player;
            }
        }

        // 左上到右下，行列相等
        if (row == col) {
            for (int i = 0; i < n; i++) {
                if (arr[i][i] != player) {
                    break;
                } else if (i == n - 1) {
                    return player;
                }
            }
        }

        // 右上到左下，行+列 == n-1
        if (row + col == n - 1) {
            for (int i = 0; i < n; i++) {
                if (arr[n - i - 1][i] != player) {
                    break;
                } else if (i == n - 1) {
                    return player;
                }
            }
        }
        return 0;
    }
}

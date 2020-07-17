package com.leetcode.solution;

class _419_battleships_in_a_board {
    // O(N), O(1) 关键词，不相邻！
    public int countBattleships(char[][] board) {
        int result = 0;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                if (board[i][j] == 'X')
                    //统计所有战舰最左上方的那个位置
                    //所以某个点的左方或上方是X就证明是同一个所以不会result++
                    if ((i == 0 || board[i - 1][j] != 'X') && (j == 0 || board[i][j - 1] != 'X'))
                        result++;

        return result;
    }
}

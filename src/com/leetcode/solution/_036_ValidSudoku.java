package com.leetcode.solution;

import java.util.*;

/**
 * Time complexity : O(81*3) ~ O(1) since all we do here is just one iteration over the board with 81 cells.
 * Space complexity : O(9 *3) ~ O(1).
 * 周期的数学表示就是取模运算mod。
 * 因此第i个九宫格的第j个格点的列号可表示为i%3*3+j%3（每个小九宫格j都是从0~9递增）
 */

public class _036_ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        // 每一个i验证第i行，第i列，第i个小cube(左上到右下)
        for (int i = 0; i < 9; i++) {
            HashSet<Character> row = new HashSet<>();
            HashSet<Character> column = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                // 检查第i行，在横坐标位置，如果不存在add返回true
                if (board[i][j] != '.' && !row.add(board[i][j]))
                    return false;

                // 检查第i列，在纵坐标位置
                if (board[j][i] != '.' && !column.add(board[j][i]))
                    return false;

                // 行号+偏移量
                int RowIndex = 3 * (i / 3) + j / 3;
                // 列号+偏移量
                int ColIndex = 3 * (i % 3) + j % 3;
                //每个小九宫格，总共9个
                if (board[RowIndex][ColIndex] != '.'
                        && !cube.add(board[RowIndex][ColIndex]))
                    return false;
            }
        }
        return true;
    }
}

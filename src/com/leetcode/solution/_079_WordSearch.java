package com.leetcode.solution;

/**
 * 图中单词查找, 参考212word search II
 * 参考200岛屿
 * 递归解法，1，注意不能返回走，用used；2，查找四个方向递归
 * <p>
 * Time complexity :O(m*n*4^l). l为单词长度
 * Space complexity :O(m*n+l).
 */

public class _079_WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null) return false;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++)
                if (existHelper(board, word.toCharArray(), 0, col, row)) return true;
        }
        return false;
    }

    private boolean existHelper(char[][] board, char[] word, int index, int col, int row) {
        if (index == word.length) return true; //长度已经达到,index为已经递归n次
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) return false; //脱离版面返回false
        if (board[row][col] != word[index]) return false; //如果字符不匹配或已使用
        char used = board[row][col]; // used保存当前格子值
        board[row][col] = 0; //当前格子值设为0，保证不能走
        boolean exist = existHelper(board, word, index + 1, col + 1, row) //四个方向有一个返回true即可
                || existHelper(board, word, index + 1, col - 1, row)
                || existHelper(board, word, index + 1, col, row + 1)
                || existHelper(board, word, index + 1, col, row - 1);
        if (exist) return true;
        board[row][col] = used; //如果没找到，放回格子的值
        return false;
    }
}

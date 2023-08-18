package com.leetcode.solution;

/**
 * 图中单词查找, 参考212word search II
 * 参考200岛屿
 * 递归解法，1，注意不能返回走，用used；2，查找四个方向递归
 * <p>
 * Time complexity :O(m*n*4^l). l为单词长度
 * Space complexity :O(m*n+l).
 * <p>
 * 思路： dfs each character in matrix to see if it can generate a path that match string.
 * Make the visited node along the path invalid char by changing to '*', so we can avoid repeated search. Once return change back the letter to original.
 * (Or extra space -- Use a visited[][] matrix to mark each visited node along the path)
 * <p>
 * Complexity: time O(mn*4^k) where k is the length of the string; mn for loop and for the dfs method its 4^k.Since the dfs method goes only as deep as the word length we have T(k)=4(T(k-1))=4*4T(k-2)=....=.. which will be 4^k.
 * space O(4mn) if the function call stack is taken into account. In each cell, we recursively call its 4four neighbors and there are mn cells in total.
 * 模板dfs,主函数遍历，首字母和ij等则&&dfs,boolean(r,c,index)函数，index到长度返T,点不同返F，取点修改为*记录已访问，四方向递归or(相等一个就行)，还原回*字母
 */

public class _079_Word_Search {
    private final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // time O(mn*4^k) where k is the length of the string ,space O(4mn)
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 先找到第一个字母, 没有charAt(0)也能通过，但加上后就不需要再进行下一步
                // word.charAt(0) == board[i][j]省略无效计算
                if ((word.charAt(0) == board[i][j]) && dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        // for loop离开还没找到就是false
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int index) {
        // 达到长度则为true
        if (index == word.length()) {
            return true;
        }
        // 不符合
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length
                || word.charAt(index) != board[r][c]) {
            return false;
        }
        // *代表已访问
        board[r][c] = '*';
        // 四个方向
        for (int[] dir : DIRS) {
            if (dfs(board, word, r + dir[0], c + dir[1], index + 1)) {
                return true;
            }
        }
        // 如果没能true则还原字符
        board[r][c] = word.charAt(index);
        return false;
    }
}

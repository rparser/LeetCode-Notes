
//这道题有一个限制条件：战舰不会相邻，
// 所以我们通过判断X点的左边的点和上面的点是不是X就可以统计所有战舰最左上方的那个位置，从而完成进阶的要求。
class Solution {
    public int countBattleships(char[][] board) {
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X')
                    if ((i == 0 || board[i - 1][j] != 'X') && (j == 0 || board[i][j - 1] != 'X'))
                        ans++;
            }
        }
        return ans;
    }
}

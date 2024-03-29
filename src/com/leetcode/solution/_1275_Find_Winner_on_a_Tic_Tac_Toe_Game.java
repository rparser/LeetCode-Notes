package com.leetcode.solution;

public class _1275_Find_Winner_on_a_Tic_Tac_Toe_Game {
    //虽然有A、B、Pending、Draw四种答案的可能。我们首先判断A、B谁能赢，再讨论A、B都未胜的情况下游戏是结束了还是继续进行；
    //判断A、B是否有人能取胜，只需要判断最后一个落棋的人是否能胜；（因为要是另外一个人赢了，游戏就结束了，不再有继续下棋的机会）
    //用数组记录最后落棋者的走棋情况，如果等于三，游戏结束，此人胜利；（以3x3为例，其余可以类推）
    //最后落棋者为未胜时，棋盘被下满则Draw，棋盘未下满则Pending。

    public String tictactoe(int[][] moves) {
        int m = moves.length;
        // 用数组记录0-2行、0-2列、正对角线、副对角线是否已满3个棋子
        // count[0-2]对应0-2行、count[3-5]对应0-2列、count[6]对应正对角线、count[7]对应副对角线
        int[] count = new int[8];
        // 思路第2步已解释为何只需考虑最后一个落棋的人
        // 倒序统计此人走棋情况
        for (int i = m - 1; i >= 0; i -= 2) {
            // 此棋对行的影响
            count[moves[i][0]]++;
            // 此棋对列的影响
            count[moves[i][1] + 3]++;
            // 此棋对正对角线的影响
            if (moves[i][0] == moves[i][1])
                count[6]++;
            // 此棋对副对角线的影响 (
            // 此处为3x3的情况，其余大小的棋盘可以类推
            if (moves[i][0] + moves[i][1] == 2)
                count[7]++;
            // 满3个棋子则胜利
            if (count[moves[i][0]] == 3 || count[moves[i][1] + 3] == 3 ||
                    count[6] == 3 || count[7] == 3)
                // A先B后 则总长度为偶时 最后为B 反之为A
                return m % 2 == 0 ? "B" : "A";
        }
        // 未胜时，棋盘未下满则继续
        if (moves.length < 9)
            return "Pending";
        // 未胜时，棋盘下满则平局结束
        return "Draw";
    }
}

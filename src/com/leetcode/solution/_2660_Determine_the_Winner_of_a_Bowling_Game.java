package com.leetcode.solution;

public class _2660_Determine_the_Winner_of_a_Bowling_Game {
    public int isWinner(int[] player1, int[] player2) {
        int sum1 = score(player1);
        int sum2 = score(player2);
        if (sum1 > sum2) {
            return 1;
        }
        if (sum1 < sum2) {
            return 2;
        }
        return 0;
    }

    public int score(int[] scores) {
        int flag = 0, sum = 0;
        for (int s : scores) {
            //如果flag为0即直接相加
            if (flag == 0) {
                sum = sum + s;
                //flag>0说明需要加当前值的两倍,维护flag：减1
            } else if (flag > 0) {
                sum = sum + 2 * s;
                flag--;
            }
            //遇到10的时候flag值刷新
            if (s == 10) {
                flag = 2;
            }
        }
        return sum;
    }
}

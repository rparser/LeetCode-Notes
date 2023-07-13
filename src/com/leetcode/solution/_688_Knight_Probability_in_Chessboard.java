package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.Arrays;

/**
 * Time Complexity: O(N^6log(K)) where N, KN,K are defined as in the problem. There are approximately N^2/8
 * canonical states, which makes our matrix multiplication O(N^6).
 * To find the K-th power of this matrix, we make O(log(K)) matrix multiplications.
 * <p>
 * Space Complexity: O(N^4). The matrix has approximately N^4/64 elements.
 **/

public class _688_Knight_Probability_in_Chessboard {
    public double knightProbability(int N, int K, int r, int c) {
        int[][] moves = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}}; //八个方向
        double[][] dp0 = new double[N][N];
        for (double[] row : dp0) {
            Arrays.fill(row, 1);
        } //设置原始值均为1
//        for (int i=0;i<dp0.length;i++) {
//            for (int j=0;j<dp0[0].length;j++) {
//                System.out.println(dp0[i][j]);
//            }
//        }

        for (int step = 0; step < K; step++) { //一共K步
            double[][] dp1 = new double[N][N]; //dp1存储
            for (int i = 0; i < N; i++) { //横向N
                for (int j = 0; j < N; j++) { //纵向N
                    for (int[] move : moves) { //遍历每一种move可能
                        int row = i + move[0]; //横向变化
                        int col = j + move[1]; //纵向变化
                        if (isIllegal(row, col, N)) { //当
                            dp1[row][col] += dp0[i][j]; //更新dp1的值,dp0为上一步的值
                        }
                    }
                }
            }
            dp0 = dp1; //dp0存储这一步的值，用来计算dp1下一步的值
        }
        return dp0[r][c] / Math.pow(8, K); //一共有8的次方的可能
    }

    private boolean isIllegal(int row, int col, int len) { //判断是否为合法move, row, column必须都大于0且小于棋盘大小N
        return row >= 0 && row < len && col >= 0 && col < len;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._688KnightProbabilityinChessboard");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(0.25, knightProbability(5, 2, 3, 4), 0.2);
    }
}

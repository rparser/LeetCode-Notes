package com.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 要想寻找棋盘上所有的皇后，得从多个角度去遍历寻找，根据题目要求每行每列主副对角线不得有多个皇后
 * 那么我们就定义列，主副对角线数组来记录遍历到当前元素下的三个方向是否有第二个皇后
 * 然后要清楚知道 n × n 的棋盘上对角线数为 2 * n - 1
 * 接下来就是利用递归 + 回溯的思想挨个遍历每行当中符合要求的位置，当遍历到最后一行即找到了一组解，将其放入 row 中存储，然后再通过
 * changeBoard 方法将找出来的位置转化为棋盘格形式存储，并放入 ans 结果集中
 * 这样遍历完所有元素之后就会找到所有满足要求的解返回 ans 即可。
 * 时间复杂度：O(N!). 放置第 1 个皇后有 N 种可能的方法，放置两个皇后的方法不超过 N (N - 2) ，放置 3 个皇后的方法不超过 N(N - 2)(N - 4) ，
 * 以此类推。总体上，时间复杂度为 O(N!) .
 * 空间复杂度：O(N) . 需要保存对角线和行的信息。
 */

//O(N!),O(N)
class _051_N_Queens {
    // 定义列和主副对角线
    private boolean[] col;
    private boolean[] dia1;
    private boolean[] dia2;
    private final List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        col = new boolean[n];
        // 对角线个数为 2 * n - 1
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        // qIndex储存每一行的queen的index
        List<Integer> qIndex = new LinkedList<>();
        // 回溯寻找符合要求的每组解
        backtracking(n, 0, qIndex);
        return result;
    }

    // curRow 代表当前访问的行数,最多到 n; qIndex 用来存放满足题意的一种情况
    private void backtracking(int n, int curRow, List<Integer> qIndex) {
        // 如果遍历到了最后一行，即代表已经找出一组解出来
        if (curRow == n) {
            // 将找到的一组解转化为棋盘格的形式后再放入 ans
            result.add(changeBoard(n, qIndex));
            return;
        }
        // 遍历当前 curRow 行的所有位置(从前往后依次遍历)
        for (int i = 0; i < n; i++) {
            // curRow + i 表示横纵坐标相加
            // curRow - i + n - 1 表示横纵坐标之差
            // 如果当前位置元素与他同一列，同一主副对角线上没有重复元素
            if (!col[i] && !dia1[curRow + i] && !dia2[curRow - i + n - 1]) {
                // 则该位置即皇后放置的位置，放入 qIndex 存储位置信息，并标记为 true
                qIndex.add(i);
                // 此时在该元素所在的列和主副对角线上就不能在遍历找到其他元素了
                // 即标记为 true
                col[i] = true;
                dia1[curRow + i] = true;
                dia2[curRow - i + n - 1] = true;

                // 接着递归寻找下一行
                backtracking(n, curRow + 1, qIndex);

                // 遍历完后再退出标记
                col[i] = false;
                dia1[curRow + i] = false;
                dia2[curRow - i + n - 1] = false;
                // 回退上一格子(回溯)
                qIndex.remove(qIndex.size() - 1);
            }
        }
    }

    // 将找到的一组解转化为棋盘格形式存储
    private List<String> changeBoard(int n, List<Integer> row) {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] ch = new char[n];
            // 初始化 ch 中所有位置元素为 ‘.’
            Arrays.fill(ch, '.');
            // 将 row 中已经确定下来的 Queen 位置改为 ‘Q’
            ch[row.get(i)] = 'Q';
            // 然后放入 tmp 中
            temp.add(new String(ch));
        }
        return temp;
    }
}

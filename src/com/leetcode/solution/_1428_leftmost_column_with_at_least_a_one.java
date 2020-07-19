package com.leetcode.solution;

import java.util.*;

class _1428_leftmost_column_with_at_least_a_one {
    // O(m+n), O(1)
    //每行向右增序，每列向下增序
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimension = binaryMatrix.dimensions();
        int r = dimension.get(0);
        int c = dimension.get(1);

        //从右上开始，如果找到0向下一行，否则向左直到不是0
        //flag判断是否找到
        boolean flag = false;
        // 先假设全为1
        int count = c - 1;
        //j从count开始，重要
        for (int i = 0; i < r; i++)
            for (int j = count; j >= 0; j--) {
                // 如果不是1，直接跳到下一行，如果本列有1，一定可以找到
                if (binaryMatrix.get(i, j) == 0)
                    break;
                else {
                    //flag变true为找到
                    //如果本列找到1，则最左列就能-1，count--
                    flag = true;
                    count--;
                }
            }
        //如果全为0
        if (!flag)
            return -1;
        //count的下一列
        else
            return count + 1;
    }

    interface BinaryMatrix {
        public int get(int row, int col);

        public List<Integer> dimensions();
    }
}
package com.leetcode.PureStorage;

public class BuddySystem {
    public static void clearBit(int[][] matrix,  int offset, int length) {
        int curLevel = matrix.length-1;
        int left = offset;
        int right = offset+length - 1;
        while (curLevel >= 0){
            for (int i = left; i <= right; i++) {
                matrix[curLevel][i] = 0;
            }
            curLevel--;
            left = left/2;
            right = right/2;
        }
    }
    public static void setBit(int[][] matrix,  int offset, int length) {
        int curLevel = matrix.length-1;
        int left = offset;
        int right = offset+length - 1;
        while (curLevel >= 0) {
            for (int i = left; i <= right; i++) {
                matrix[curLevel][i] = 1;
            }
            int leftBuddy = left + (left % 2 == 1 ? -1 : 1);
            int rightBuddy = right + (right % 2 == 1 ? -1 : 1);
            int leftBit = matrix[curLevel][left] * matrix[curLevel][leftBuddy]; //上一层的值
            int rightBit = matrix[curLevel][right] * matrix[curLevel][rightBuddy];
            curLevel--;
            left /= 2;
            right /= 2;
            if (leftBit == 0) { //上一层为0则弃掉不计算
                left++;
            }
            if (rightBit == 0) {
                right--;
            }
        }
    }
}

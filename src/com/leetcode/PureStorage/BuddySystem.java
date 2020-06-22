package com.leetcode.PureStorage;

import java.util.*;

public class BuddySystem {
    //提问 clear是否要求下一层children全变为0还是只变一个为0
    public static void clear(int[] arr, int offset, int length) {
        int n = arr.length - 1;
        for (int i = offset; i <= offset + length - 1; i++) {
            //improvement,可以设置另一个arr全为0，一旦计算过则变为1
            // if(visited[i] == 1) continue;
            // 或是节省空间，如果可以是integer array，则可以本array计算过则+2
            // if(arr[i] >=2) continue;
            arr[i] = 0;
            //如果某一个数为零且下面所有均变为0则用注释代码
            int cIdx = i;
//            int level = 2;
            while (2 * cIdx + 1 <= n) {
//                for (int l = 1; l <= level; l++)
//                    if (2 * cIdx + l <= n)
                arr[2 * cIdx + 1] = 0;
//                        arr[2 * cIdx + l] = 0;
//                    else
//                        break;
                cIdx = 2 * cIdx + 1;
//                level *= 2;
            }
            int pIdx = i;
            while (pIdx > 0) {
                //如果已经为0则无须再往上检查因为已经是0
                if (arr[(pIdx - 1) / 2] == 0) break;
                arr[(pIdx - 1) / 2] = 0;
                pIdx = (pIdx - 1) / 2;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void set(int[] arr, int offset, int length) {
        int n = arr.length - 1;
        for (int i = offset; i <= offset + length - 1; i++) {
            arr[i] = 1;
            // 实际就是clear全变0的代码，所以可以重用为update(int[] arr, int offset, int length, int updateTo)
            int cIdx = i;
            int level = 2;
            while (2 * cIdx + 1 <= n) {
                for (int l = 1; l <= level; l++)
                    if (2 * cIdx + l <= n)
                        arr[2 * cIdx + l] = 1;
                    else
                        break;
                cIdx = 2 * cIdx + 1;
                level *= 2;
            }
            int pIdx = i;
            while (pIdx > 0) {
                //如果上级已经是1则不需要计算跳出
                if (arr[(pIdx - 1) / 2] == 1) break;
                //否则计算sibling
                int sibling = pIdx + (pIdx % 2 == 1 ? 1 : -1);
                //如果sibling为0，则自己变为1也没用，跳出
                if (sibling <= n && arr[sibling] == 0) break;
                else arr[(pIdx - 1) / 2] = 1;
                pIdx = (pIdx - 1) / 2;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static boolean continousEight1s(int[] arr){
        int n = arr.length;
        int level = (int)(Math.log(n + 1) / Math.log(2)) + 1;
        if(level < 3) return false;
        if((n - 2 ^ level) < 8) return false;
        // 第一层是1则下面全是1 或是从倒数第4层算起 时间复杂度一样都为2^k因为每行数量都是上面所有的数量和-1
        return true;
    }
    public static void clearBit(int[][] matrix, int offset, int length) {
        int curLevel = matrix.length - 1;
        int left = offset;
        int right = offset + length - 1;
        while (curLevel >= 0) {
            for (int i = left; i <= right; i++)
                matrix[curLevel][i] = 0;

            curLevel--;
            left = left / 2;
            right = right / 2;
        }
    }

    public static void setBit(int[][] matrix, int offset, int length) {
        int curLevel = matrix.length - 1;
        int left = offset;
        int right = offset + length - 1;
        while (curLevel >= 0) {
            for (int i = left; i <= right; i++)
                matrix[curLevel][i] = 1;

            int leftBuddy = left + (left % 2 == 1 ? -1 : 1);
            int rightBuddy = right + (right % 2 == 1 ? -1 : 1);
            int leftBit = matrix[curLevel][left] * matrix[curLevel][leftBuddy]; //上一层的值
            int rightBit = matrix[curLevel][right] * matrix[curLevel][rightBuddy];
            curLevel--;
            left /= 2;
            right /= 2;
            if (leftBit == 0)  //上一层为0则弃掉不计算
                left++;

            if (rightBit == 0)
                right--;
        }
    }
}

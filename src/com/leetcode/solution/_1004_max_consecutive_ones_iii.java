package com.leetcode.solution;

/**
 * 使用双指针 left 和 right 指代窗口的左右边界，移动 right 指针遍历整个数组，
 * 同时维护一个变量 max，每次 right 移动计算一次当前的最大值。
 * 分为以下几种情况：
 * 1.当 A[right]=1时，left 不变，right 继续移动
 * 2.当 A[right]=0时，
 * *****2.1 0的数量在 K 的范围内，left 不变，right 继续移动
 * *****2.2 0的数量>K，
 * **********2.2.1当 A[left]==0时，即 left 指向了一个零，只需要 left 右移一格，就可以减少一个零
 * **********2.2.2当 A[left]==1时，即此时窗口内包了 K 个零，需要先移动至下个零，再右移一格才能减少一个零
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 **/
class _1004_max_consecutive_ones_iii {
    // K个值可以用来从0变为1
    // O(n),O(1)
    //记得left 必须要退出一个0
    public int longestOnes(int[] A, int K) {
        int max = 0;
        //双指针,例子0,0,0 k=2
        for (int left = 0, right = 0; right < A.length; right++) {
            if (A[right] == 0) {
                //K 还有就先用
                if (K > 0)
                    K--;
                    // 如果K已经用光（== 0）
                //（必须还回一个0，因为right已经进入下一个loop且没有K可用）
                else {
                    //一直到left不为1为止,
                    while (A[left] == 1)
                        left++;

                    // 下一个left
                    // （必须还回一个0，因为right已经进入下一个loop且没有K可用）
                    left++;
                }

            }
            //if (A[right] == 0) {}loop之外
            max = Math.max(right - left + 1, max);
        }
        return max;
    }
}
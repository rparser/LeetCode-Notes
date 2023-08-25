package com.leetcode.solution;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 按照 i mod ki\bmod kimodk 的结果将 aaa 分组，对每一组（记作 bbb），我们需要解决：
 *
 * 让数组 bbb 的所有元素相等的最少运算次数。
 *
 * 根据中位数贪心，将 bbb 的所有元素变为 bbb 的中位数是最优的。
 *
 * 证明：设 bbb 的长度为 mmm，设要将所有 b[i]b[i]b[i] 变为 xxx。
 * 假设 bbb 已经从小到大排序。首先，如果 xxx 取在区间 [b[0],b[m−1]][b[0],b[m-1]][b[0],b[m−1]] 之外，
 * 那么 xxx 向区间方向移动可以使距离和变小；同时，如果 xxx 取在区间 [b[0],b[m−1]][b[0],b[m-1]][b[0],b[m−1]] 之内，
 * 无论如何移动 xxx，它到 b[0]b[0]b[0] 和 b[m−1]b[m-1]b[m−1]
 * 的距离和都是一个定值 b[m−1]−b[0]b[m-1]-b[0]b[m−1]−b[0]，
 * 那么去掉 b[0]b[0]b[0] 和 b[m−1]b[m-1]b[m−1] 这两个最左最右的数，问题规模缩小。
 * 不断缩小问题规模，如果最后剩下 111 个数，那么 xxx 就取它；如果最后剩下 222 个数，
 * 那么 xxx 取这两个数之间的任意值都可以（包括这两个数）。因此 xxx 可以取 b[m/2]b[m/2]b[m/2]。
 *
 */

public class _2607_Make_K_Subarray_Sums_Equal {
    public long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length;
        k = gcd(k, n);
        long result = 0;
        for (int i = 0; i < k; ++i) {
            var b = new ArrayList<Integer>();
            for (int j = i; j < n; j += k) {
                b.add(arr[j]);
            }
            Collections.sort(b);
            int mid = b.get(b.size() / 2);
            for (int x : b) {
                result += Math.abs(x - mid);
            }
        }
        return result;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
}

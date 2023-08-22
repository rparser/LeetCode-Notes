package com.leetcode.solution;

import java.util.Arrays;

public class _945_Minimum_Increment_to_Make_Array_Unique {
    // O(nlogn)
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A); // 先排序
        int curmax = -1; // 当前数组最大值
        int res = 0;
        for (int a : A) {
            if (a <= curmax) {
                // 当前元素 A[i] 需要增加到 curmax + 1
                res += (curmax + 1 - a); // 记录自增次数
            }
            curmax = Math.max(curmax + 1, a);
        }
        return res;
    }

    // O(n+k) k是整数取值范围
    public int minIncrementForUniqueNplusK(int[] A) {
        int[] count = new int[40000];
        int max = 0;
        for (int a : A) {
            count[a]++; // 计数
            max = Math.max(max, a); // 计算数组中的最大值
        }

        int res = 0;
        for (int j = 0; j < max; j++) {
            if (count[j] > 1) {
                // 有 count[j] - 1 个数需要增加
                res += count[j] - 1;
                count[j + 1] += count[j] - 1;
            }
        }

        // count[max] 单独计算，是因为可能超出 40000 的边界
        if (count[max] > 1) {
            int d = count[max] - 1;
            // 有 d 个数需要增加
            // 分别增加为 max + 1, max + 2, ... max + d
            // 使用等差数列公式求和
            res += (1 + d) * d / 2;
        }

        return res;
    }
}

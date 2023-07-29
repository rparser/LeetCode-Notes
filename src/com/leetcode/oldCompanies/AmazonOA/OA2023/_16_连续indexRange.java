package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.Arrays;

public class _16_连续indexRange {
    public static int maxLengthInRange(int[] arr1, int[] arr2, int limit) {
        int n = arr1.length;
        int[][] pairs = new int[n][2];

        // 将arr1和arr2组合成pairs数组，用于排序
        for (int i = 0; i < n; i++) {
            pairs[i] = new int[]{arr1[i], arr2[i]};
        }

        // 按照arr1的值从小到大排序pairs数组
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        int left = 0;
        int right = -1;
        int maxLen = 0;
        int currentSum = 0;

        while (right < n - 1) {
            right++;
            currentSum += pairs[right][1];

            while (left <= right && pairs[right][0] + (right - left + 1) * currentSum > limit) {
                currentSum -= pairs[left][1];
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 1, 1, 1};
        int[] arr2 = {1, 1, 1, 1, 1};
        int limit = 10;
        int result = maxLengthInRange(arr1, arr2, limit);
        System.out.println("满足条件的最大length(i...j): " + result); // 输出：4
    }
}

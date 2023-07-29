package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.Arrays;

public class _17_intern_testRanking {

    public static int[] getMeanRankCount2(int[] rank) {
        int n = rank.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int curSum = 0;
                int k = i;
                for (; k <= j; k++) {
                    System.out.println(j + "  aaaaa  " + k);
                    curSum += rank[k];
                }
                System.out.println(j + " here   " + k);
                if (curSum != 0 && curSum % (j - i + 1) == 0) {
                    System.out.println("current  " + curSum + "   divide   " + (j - i + 1));
                    result[curSum / (j - i + 1) - 1]++;
                }
            }
        }

        return result;
    }

    public static int[] getMeanRankCount(int[] rank) {
        int n = rank.length;
        int[] result = new int[n];
        int[] sum = new int[n];

        // 计算前缀和数组
        sum[0] = rank[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + rank[i];
        }
        // 遍历所有可能的平均值x

        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                int subArraySum = sum[j] - (i > 0 ? sum[i - 1] : 0);
                int subArrayLength = j - i + 1;

                if (subArraySum != 0 && subArraySum % subArrayLength == 0) {
                    // 找到一个满足条件的子数组，计数加1
                    result[subArraySum / subArrayLength - 1]++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
//        int[] rank = {3, 1, 4, 2, 5};
//        int[] result = getMeanRankCount(rank);
//        System.out.println("每个平均排名对应的小组数量：" + Arrays.toString(result)); // 输出：[0, 1, 1, 1, 1]
        int[] rank2 = {1, 2, 3, 4, 5};
        int[] result2 = getMeanRankCount(rank2);
        System.out.println("每个平均排名对应的小组数量：" + Arrays.toString(result2)); // 输出：[0, 1, 1, 1, 1]

        int[] rank3 = {4, 7, 3, 6, 5, 2, 1};
        int[] result3 = getMeanRankCount(rank3);
        System.out.println("每个平均排名对应的小组数量：" + Arrays.toString(result3)); // 输出：[0, 1, 1, 1, 1]
    }
}

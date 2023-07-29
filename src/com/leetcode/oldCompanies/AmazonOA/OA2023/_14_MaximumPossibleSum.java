package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.Arrays;

public class _14_MaximumPossibleSum {
    public static int maxQualitySum(int[] packets) {
        Arrays.sort(packets); // 将数据包按大小进行排序

        int n = packets.length;
        int numChannels = n / 2 + 1; // 计算通道数

        int totalSum = 0;
        for (int i = n - 1; i >= n - numChannels; i--) {
            totalSum += packets[i]; // 分配最大的数据包给通道，累加总和
        }

        // 如果数据包个数为偶数，还需要考虑中间两个元素的情况
        if (n % 2 == 0) {
            int medianSum = packets[n / 2] + packets[n / 2 - 1]; // 计算中间两个元素的和
            totalSum = Math.max(totalSum, medianSum); // 与之前的总和进行比较，取较大值
        }

        return (int) Math.ceil(totalSum / (double) numChannels); // 返回质量总和的上取整值
    }

    public static void main(String[] args) {
        int[] packets = {2, 5, 1, 3, 4};
        System.out.println(maxQualitySum(packets)); // 输出: 8
    }
}

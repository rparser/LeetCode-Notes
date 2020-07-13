package com.leetcode.solution;

import java.util.*;

class _300_longest_increasing_subsequence {
    // Greedy+binary search O(NlogN), O(N)
    // 尽可能的减缓增长速度
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] arr = new int[n];
        // result实际是数组被填上的个数
        int res = 0;
        for (int num : nums) {
            //在当前已填满的位置找,左开右闭合不包括res
            int index = Arrays.binarySearch(arr, 0, res, num);
            //如果找到证明已存在则不符合升序要求
            //  找不到返回index = (-(insertion point) - 1)
            // so insertion point = -index - 1
            if (index < 0)
                index = -index - 1;
            //把这个nums[i]放在插入位置上，数组可能不单调[9,10,2]最后会是[2,10]
            arr[index] = num;
            // 只有是放在了最后一个位置(长度增加)，才会增加res
            if (index == res)
                res++;
        }
        return res;
    }

    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        //dp[i]代表到数组第i个位置的最长上升子序列
        int[] dp = new int[n + 1];
        int res = 1;
        //枚举第i个位置
        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
            //枚举它所有前面位置
            for (int j = 1; j < i; j++) {
                //如果第i个位置的数比前面数字大，符合最长上升子序列，更新
                if (nums[j - 1] < nums[i - 1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                //记录全局最大值
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}
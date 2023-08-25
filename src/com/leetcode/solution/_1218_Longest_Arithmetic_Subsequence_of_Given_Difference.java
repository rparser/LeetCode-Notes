package com.leetcode.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义 f[i][j]（j 非 0 即 1） 为代表考虑前 i 个数，且第 i 个数的选择情况为 j 时，得到的最长定差子序列长度。
 * 最终答案为 max⁡(f[n−1][0],f[n−1][1])\max(f[n - 1][0], f[n - 1][1])max(f[n−1][0],f[n−1][1])，
 * 同时我们有显然的初始化条件 f[0][0]=0 和 f[0][1]=1
 *
 * 这引导我们在转移过程中使用「哈希表」记录处理过的位置的值信息。
 *
 * f[i][0]：明确了第 iii 个不选，那么此时最大长度为前一个位置的结果。即有：
 * f[i][0]=max⁡(f[i−1][0],f[i−1][1])f[i][0] = \max(f[i - 1][0], f[i - 1][1])
 * f[i][0]=max(f[i−1][0],f[i−1][1])
 * f[i][1]f[i][1]f[i][1]：明确了第 iii 个要选，此时进行分情况讨论：
 *
 * arr[i]arr[i]arr[i] 独立成为一个子序列，此时有：f[i][1]=1f[i][1] = 1f[i][1]=1；
 *
 * arr[i]arr[i]arr[i] 接在某一个数的后面，由于给定了差值 differencedifferencedifference，
 * O(N)
 */

public class _1218_Longest_Arithmetic_Subsequence_of_Given_Difference {
    public int longestSubsequence(int[] arr, int d) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        int[][] f = new int[n][2];
        f[0][1] = 1;
        map.put(arr[0], 0);

        for (int i = 1; i < n; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
            f[i][1] = 1;
            int prev = arr[i] - d;
            if (map.containsKey(prev)) {
                f[i][1] = Math.max(f[i][1], f[map.get(prev)][1] + 1);
            }
            map.put(arr[i], i);
        }
        return Math.max(f[n - 1][0], f[n - 1][1]);
    }
}

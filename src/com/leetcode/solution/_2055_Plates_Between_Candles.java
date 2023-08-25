package com.leetcode.solution;

/**
 * 前缀和
 * 更进一步，在给定 s 的前提下，每个位置其左边和右边最近的蜡烛唯一确定。
 * <p>
 * 我们可以在预处理前缀和的同时，预处理每个位置左右最近的蜡烛下标，从而省去每次二分。
 * 时间复杂度：O(n+m)O(n + m)O(n+m) 令 s 的长度为 nnn，qs 长度为 mmm
 * 空间复杂度：O(n)O(n)O(n)
 */
public class _2055_Plates_Between_Candles {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int[] result = new int[queries.length];
        int n = s.length();
        int[] presum = new int[n + 1], lefts = new int[n], rights = new int[n];
        for (int i = 0, j = n - 1, l = -1, r = -1; i < n; i++, j--) {
            if (s.charAt(i) == '*')
                presum[i + 1] = presum[i] + 1;
            else {
                presum[i + 1] = presum[i];
                l = i;
            }
            if (s.charAt(j) == '|') {
                r = j;
            }
            lefts[i] = l;
            rights[j] = r;
        }

        for (int i = 0; i < queries.length; i++) {
            if (lefts[queries[i][1]] >= 0 && rights[queries[i][0]] >= 0 && lefts[queries[i][1]] > rights[queries[i][0]]) {
                result[i] = presum[lefts[queries[i][1]]] - presum[rights[queries[i][0]] + 1];
            }
        }
        return result;
    }
}

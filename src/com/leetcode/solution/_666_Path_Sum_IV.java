package com.leetcode.solution;

import java.util.Arrays;

public class _666_Path_Sum_IV {
    public int pathSum(int[] nums) {
        int[][] paths = new int[6][10];
        for (int[] path : paths) {
            Arrays.fill(path, -1);
        }
        for (int n : nums) {
            paths[n / 100][n % 100 / 10] = n % 10; // 得到第一位和第二位，最后一位是%10
        }
        // temporary hold the result
        int[] res = new int[]{0};
        dfs(paths, 1, 1, res, 0);
        return res[0];
    }

    private void dfs(int[][] paths, int level, int pos, int[] res, int sum) {
        if (level > 5 || pos > 9 || paths[level][pos] == -1) {
            return;
        }
        sum += paths[level][pos];
        int nextLeft = pos * 2 - 1 > 9 ? -1 : paths[level + 1][pos * 2 - 1];
        int nextRight = pos * 2 > 9 ? -1 : paths[level + 1][pos * 2];
        // if at the end of a path, add the sum to the res
        if (level == 5 || Math.max(nextLeft, nextRight) == -1) {
            res[0] += sum;
        }
        dfs(paths, level + 1, pos * 2 - 1, res, sum);
        dfs(paths, level + 1, pos * 2, res, sum);
    }
}

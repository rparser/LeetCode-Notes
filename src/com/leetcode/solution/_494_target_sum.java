package com.leetcode.solution;

//可以将dp[ i ][ j ]定义为从数组nums中 0 - i 的元素进行加减可以得到 j 的方法数量。
// dp[ i ][ j ] = dp[ i - 1 ][ j - nums[ i ] ] + dp[ i - 1 ][ j + nums[ i ] ]
class _494_target_sum {
    //O(mn), O(mn)
    public static int findTargetSumWays(int[] nums, int s) {
        int sum = 0;
        for (int num : nums)
            sum += num;

        // 绝对值范围超过了sum的绝对值范围则无法得到
        if (Math.abs(s) > Math.abs(sum)) return 0;

        int len = nums.length;
        // 因为sum可以变成正数负数和0所以size是正+负+0
        // index是 0 ~ (sum -1)为负数, sum为0，sum +1 ~ 2sum 为正数
        int size = sum * 2 + 1;
        int[][] dp = new int[len][size];
        // 初始化，第一个数为0，则可为+或-
        if (nums[0] == 0)
            dp[0][sum] = 2;
            // 否则
        else {
            //根据如上sum实际为0
            dp[0][sum + nums[0]] = 1;
            dp[0][sum - nums[0]] = 1;
        }
        // 上一个格子减去这个值的可能 + 上一个格子加上这个值的可能
        // dp[ i ][ j ] = dp[ i - 1 ][ j - nums[ i ] ] + dp[ i - 1 ][ j + nums[ i ] ]
        for (int i = 1; i < len; i++)
            for (int j = 0; j < size; j++) {
                // 左侧比零小不可能 或 比右侧全正边界都大证明不存在
                int l = Math.max((j - nums[i]), 0);
                int r = (j + nums[i]) < size ? j + nums[i] : 0;
                dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
            }

        return dp[len - 1][sum + s];
    }

    // 找到nums的一个子集 P，使得sum P = (target + sum)/2
    public int findTargetSumWays1D(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        int sums = 0;
        for (int num : nums) sums += num;
        if (sums < S || (S + sums) % 2 == 1)
            return 0;

        int p = (S + sums) / 2;
        int[] dp = new int[p + 1];
        dp[0] = 1;
        for (int num : nums)
            for (int i = p; i >= num; i--)
                dp[i] += dp[i - num];

        return dp[p];
    }
}
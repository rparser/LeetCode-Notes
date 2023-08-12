package com.leetcode.solution;

/**
 * dp
 */

public class _416_Partition_Equal_Subset_Sum {
    // O(NC), O(C) C 是数组元素的和的一半
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // 找到和的一半
        sum /= 2;
        // dp是：和等于这个值是否可以
        boolean[] dp = new boolean[sum + 1];
        // 和为0是可以
        dp[0] = true;

        for (int i = 1; i <= nums.length; i++) { //从1到length所以还是length个
            // 因为i从1开始，所以是nums[i-1]
            // j必须要>=对应的cur nums[i-1]，因为
            for (int j = sum; j >= nums[i - 1]; j--) {
                // dp[j-nums[i-1]]是指不考虑nums[i-1]时就可以为true
                dp[j] = dp[j] || dp[j - nums[i - 1]];
            }
        }
        return dp[sum];
    }

    //2D 下面有一位数组 O(NC), O(NC)
    public boolean canPartition2D(int[] nums) {
        int len = nums.length;
        if (len == 0) return false;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 != 0)
            return false;
        int target = sum / 2;
        // 创建二维状态数组，row：index，index：target sum（包括 0, 所以需要+1）
        //dp[4][9] = true 含义是对于给定的集合中，若只对前 4 个数字进行选择，存在一个子集的和可以恰好凑出 9
        boolean[][] dp = new boolean[len][target + 1];

        // 再填表格后面几行
        for (int i = 1; i < len; i++) {
            for (int curSum = 0; curSum <= target; curSum++) {
                // 上一行是true则这一行必为true
                dp[i][curSum] = dp[i - 1][curSum];
                //如果上一个能找到，则这个必能找到
                // 或 上一个能找到curSum-num[i]当前值，则这个也必能找到
                if (nums[i] < curSum)
                    dp[i][curSum] = dp[i - 1][curSum] || dp[i - 1][curSum - nums[i]];
                // 如果当前num[i]等于sum j则必为true
                if (nums[i] == curSum)
                    dp[i][curSum] = true;
                // 找到立刻返回
                if (dp[i][target])
                    return true;
            }
        }
        return dp[len - 1][target];
    }
}

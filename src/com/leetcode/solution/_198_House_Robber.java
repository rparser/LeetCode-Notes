package com.leetcode.solution;

/**
 * Time complexity : O(n). Assume that nn is the number of houses, the time complexity is O(n).
 * Space complexity : O(1)
 */

public class _198_House_Robber {
    // 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    // bottom up - DP O(N), O(1)
    public int robDP(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 子问题：
        // f(k) = 偷 [0..k) 房间中的最大金额

        // f(0) = 0
        // f(1) = nums[0]
        // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }
        int N = nums.length;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int k = 2; k <= N; k++) {
            dp[k] = Math.max(dp[k - 1], nums[k - 1] + dp[k - 2]);
        }

        return dp[N];
    }

    public int rob(int[] nums) {
        int prevMax = 0, currMax = 0;
        for (int x : nums) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }
}

package com.leetcode.solution;

//设dp[i]为i的二进制形式的1的个数，i是奇数时，dp[i]=dp[i-1]+1,
//        因为i是在i-1的二进制数上加了个1啊；i是偶数时，dp[i]=dp[i/2],
//        因为i就是把i/2往左移（是数左移，末尾补0）得到的，所以1的个数没变

class _338_counting_bits {
    // dp[i]为i的二进制形式的1的个数，
    // i是奇数时，dp[i]=dp[i-1]+1, 为i是在i-1的二进制数上加了个1啊
    //  i是偶数时，dp[i]=dp[i/2], 因为i就是把i/2往左移（是数左移，末尾补0）得到的，所以1的个数没变
    public int[] countBits(int num) {
        if (num == 0) return new int[]{0};
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++)
            if (i % 2 == 0)
                dp[i] = dp[i / 2];
            else
                dp[i] = dp[i - 1] + 1;

        return dp;
    }
}

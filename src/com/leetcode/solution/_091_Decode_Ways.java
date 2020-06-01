package com.leetcode.solution;

/**
 * 时间复杂度 O (n)， 因为内层循环能控制到不超过2次
 * 空间复杂度 O (n)
 */
public class _091_DecodeWays {
    //top down
    public int numDecodings(String s) {
        char[] nums = s.toCharArray();
        int len = nums.length;
        int[] dp = new int[len + 1];  // dp[i] 表示从第i+1个数到第n个数的所有方案数
        dp[len] = 1;
        // 从右往左
        for (int i = len - 1; i >= 0; i--) {
            // 注意判断0字符
            if (nums[i] == '0') continue;   // 当开始位为0字符时不满足任意一个字母的解析，跳过
            int num = 0;
            for (int j = i; j < len && j - i < 2; j++) {
                num = num * 10 + (nums[j] - '0');
                // 对子状态dp[j+1]为0开头的也可进行添加，因为没有赋值为dp[j+1]为0
                if (num <= 26) dp[i] += dp[j + 1];
            }
        }
        return dp[0];
    }
    //bottom up
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];

        dp[0] = 1;
        for (int i = 1; i < chars.length; i++) {
            char current = chars[i];
            char prev = chars[i - 1];
            // 如果最后一位不为0则继承前一位的dp值
            if (current != '0')
                dp[i] = dp[i - 1];
            // 如果能和前一位形成26个字母，则为前两位的和
            if ((prev == '1' && current >= '0' && current <= '9') ||
                    (prev == '2' && current >= '0' && current <= '6')) {
                if (i >= 2) dp[i] = dp[i] + dp[i - 2];
                else dp[i]++;
            }
        }
        return dp[chars.length - 1];
    }
}

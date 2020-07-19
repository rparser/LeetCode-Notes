package com.leetcode.solution;

/**
 * 见图， 1. 事先要把0presense的情况标好
 * 2. 填数组，watch几个关键情况
 * http://rainykat.blogspot.com/2017/02/leetcode-10-regular-expression.html
 */

//1. 字母相等 或 pc是".dot"，继承上一个
// 2. 如果pc是* dp[i][j - 2] 是true 则必然dp[i][j]也是true
// 3. pc是* dp[i][j - 2] 是false 但
public class _010_Regular_Expression_Matching {
    // O(mn), O(mn), p有特殊字符
    public boolean isMatch(String s, String p) {
        //dp[i][j] ：s前i个字符[0,i)是否能匹配p的前j个字符[0,j)。
        // 要明确一点，这里是左闭右开的，因此此时是在比较s[i-1]与p[j-1]。
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        //p字符串的第2个字符是否等于'*',此时j元素需要0个，所以s不变p减除两个字符
        //第一行处理看是否a*b*
        for (int j = 1; j < p.length() + 1; j++)
            if (p.charAt(j - 1) == '*')
                dp[0][j] = dp[0][j - 2];

        for (int i = 1; i < s.length() + 1; i++)
            for (int j = 1; j < p.length() + 1; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                //字母相等 或 pc是".dot"，继承上一个
                if (pc == sc || pc == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (pc == '*') {
                    // *可以为空，所以如果pc是* 如果dp[i][j - 2] 是 true则必然dp[i][j]也是true
                    if (dp[i][j - 2])
                        dp[i][j] = true;//0 presence of pre letter
                    // p需要能前移1个。（当前p指向的是j-1，前移1位就是j-2，因此为j>=2）
                    // 如果dp[i][j - 2]是false
                    else if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')
                        dp[i][j] = dp[i - 1][j];
                } //除此之外的情况都为false
            }
        return dp[s.length()][p.length()];
    }
}

package com.leetcode.solution;

/**
 * 见图， 1. 事先要把0presense的情况标好
 * 2. 填数组，watch几个关键情况
 * http://rainykat.blogspot.com/2017/02/leetcode-10-regular-expression.html
 */

public class _010_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        //handle 1st row dealing a*b* - 0 presence, mark such * true
        for (int j = 1; j < dp[0].length; j++) {
            if (p.charAt(j - 1) == '*')
                dp[0][j] = dp[0][j - 2];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (pc == sc || pc == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (pc == '*') {
                    if (dp[i][j - 2])
                        dp[i][j] = true;//0 presence of pre letter
                    else if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')
                        dp[i][j] = dp[i - 1][j];
                } //both different letter, fill false
            }
        }
        return dp[s.length()][p.length()];
    }
}

class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null) {
            return 0;
        }
        int[][] dp = new int[s.length()][s.length()];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        //从下往上遍历
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                //那么就说明在原先的基础上又增加了回文子序列的长度
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                //表明这时dp[i][j]只需取两者之间的最大值即可
                else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
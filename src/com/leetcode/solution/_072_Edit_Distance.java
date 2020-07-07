package com.leetcode.solution;

//时间复杂度 ：O(MN)，其中 M 为 word1 的长度，N 为 word2 的长度；
//        空间复杂度 ：O(MN)，状态表格的大小。
public class _072_Edit_Distance {
    // Top-up DP, O(MN), O(MN)
    public int minDistance(String word1, String word2) {
        char[] word1Array = word1.toCharArray();
        char[] word2Array = word2.toCharArray();
        int len1 = word1Array.length;
        int len2 = word2Array.length;
        // word1 is row and word2 is column
        // DP是，word1前多少个字母到word2前多少个字母所需的距离
        // 多开一行一列是为了保存边界条件，即字符长度为 0 的情况
        int[][] dp = new int[len1 + 1][len2 + 1];

        // 初始化：word2 is empty, then edit distance to word1 is i++
        for (int i = 1; i <= len1; i++)
            dp[i][0] = i;

        // 同理word1为空时，也要补齐
        for (int j = 1; j <= len2; j++)
            dp[0][j] = j;

        // 注意：填写 dp 数组的时候，由于初始化多设置了一行一列，横、纵坐标有个偏移
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                // 如果这个字母相等，则不变
                if (word1Array[i] == word2Array[j]) {
                    dp[i + 1][j + 1] = dp[i][j];
                    continue;
                }
                // 否则在以下三种情况中选出步骤最少的，这是「动态规划」的「最优子结构」
                // 1、在下标 i 处插入一个字符 (word1 多一个字母)
                int insert = dp[i + 1][j] + 1;
                // 2、替换一个字符
                int replace = dp[i][j] + 1;
                // 3、删除一个word1字字母或理解为添加一个word2字母（也就是删除一个word1字母）
                int delete = dp[i][j + 1] + 1;
                dp[i + 1][j + 1] = Math.min(Math.min(insert, replace), delete);
            }
        }
        return dp[len1][len2];
    }
}

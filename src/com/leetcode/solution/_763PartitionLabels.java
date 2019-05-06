package com.leetcode.solution;

import java.util.*;

/**
 * 字符串字母分组,每个字母最多一次
 * <p>
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * <p>
 * Time Complexity: O(N), where NN is the length of S.
 * Space Complexity: O(N).
 */

public class _763PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26]; // 记录字母最后的位置
        for (int i = 0; i < S.length(); i++) // 遍历第一次记录位置
            last[S.charAt(i) - 'a'] = i;
        int end = 0, start = 0; // 设置开始和结束点
        List<Integer> ans = new ArrayList<>(); // ArrayList记录结果
        for (int i = 0; i < S.length(); i++) { // 遍历第二次
            end = Math.max(end, last[S.charAt(i) - 'a']); // 不断向后推end，推到不动为止
            if (i == end) { // i==end时，开始新片段
                ans.add(i - start + 1); // 结果加入ArrayList
                start = i + 1; // start点从下一个开始
            }
        }
        return ans;
    }
}

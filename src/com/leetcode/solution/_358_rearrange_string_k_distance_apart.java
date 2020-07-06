package com.leetcode.solution;

public class _358_rearrange_string_k_distance_apart {
    public String rearrangeString(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        // nextPosition是下一个可以放的位置
        int[] nextPosition = new int[26];
        // 计数
        for (int i = 0; i < len; i++)
            count[s.charAt(i) - 'a']++;

        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < len; index++) {
            int candidate = findCandidate(count, nextPosition, index);
            if (candidate == -1)
                return "";
            count[candidate]--;
            // 下一个可以放的要更新
            nextPosition[candidate] = index + k;
            sb.append((char) ('a' + candidate));
        }
        return sb.toString();
    }

    private int findCandidate(int[] count, int[] nextPosition, int index) {
        int max = Integer.MIN_VALUE;
        int candidate = -1;
        for (int i = 0; i < count.length; i++) {
            // 此位置必须符合nextPosition
            if (count[i] > 0 && count[i] > max && index >= nextPosition[i]) {
                max = count[i];
                candidate = i;
            }
        }
        return candidate;
    }
}

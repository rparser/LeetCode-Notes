package com.leetcode.solution;

import java.util.*;

/**
 * 贪心 Greedy
 * 遇到重复的字符时，才开始划分，这样能保证划分次数最少；
 * 此处用 Set 去重，遇到重复的字符说明要在此处划分了，划分完将 Set 进行 clear，继续下一次判断。
 */
public class _2405_Optimal_Partition_of_String {
    public int partitionString(String s) {
        int result = 0;
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.add(c)) {
                result++;
                set.clear();
            }
            set.add(c);
        }
        return result + 1;
    }
}

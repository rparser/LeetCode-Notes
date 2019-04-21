package com.leetcode.solution;

import java.util.*;

/**
 * 求数组里两个数的和为某个固定值
 * <p>
 * 1.建立map
 * 2.以此在map加入数字，并查找补值，如果有补值则返回
 * 3.到结束依然没有则throw IllegalArgumentException
 */

public class _001TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; //以此在map加入数字，并查找补值，如果有补值则返回
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

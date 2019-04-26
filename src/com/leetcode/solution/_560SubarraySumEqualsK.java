package com.leetcode.solution;

import java.util.*;

/**
 * 子数组和为K
 * <p>
 * Time complexity : O(n). The entire nums array is traversed only once.
 * Space complexity : O(n). Hashmap can contain upto nn distinct entries in the worst case.
 */

public class _560SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int result = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k))
                result += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}

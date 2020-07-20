package com.leetcode.solution;

import java.util.*;

/**
 * 子数组和为K
 * <p>
 * Time complexity : O(n). The entire nums array is traversed only once.
 * Space complexity : O(n). Hashmap can contain upto nn distinct entries in the worst case.
 * 1loop，建map<当前sum，能达到这个sum的可能数>,把每个sum加入map，用containsKey(sum-k)得到result
 */

public class _560_Subarray_Sum_Equals_K {
    // O(N), O(N) 前缀和 prefix sum
    public int subarraySum(int[] nums, int k) {
        int result = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); //<当前sum，能达到这个sum的可能数>
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) //a1a2a3a4a5,若a3a4为K，则sum计算到a4时，a1a2的sum必在map里
                result += map.get(sum - k);

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}

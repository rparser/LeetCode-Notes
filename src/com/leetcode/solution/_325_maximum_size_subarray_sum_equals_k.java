package com.leetcode.solution;

import java.util.HashMap;
import java.util.Map;

class _325_maximum_size_subarray_sum_equals_k {
    public static int maxSubArrayLen(int[] nums, int k) {
        // 前缀和值（到此index时）和index，
        Map<Integer, Integer> map = new HashMap<>();
        // 是为了
        map.put(0, -1);
        int curSum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            // 如果containsKey则证明之前存过即已经找到，大小为index差值
            if (map.containsKey(curSum - k)) {
                Integer index = map.get(curSum - k);
                res = Math.max(res, i - index);
            }
            // 如果已有此curSum，证明之前放过，就不要再放，因为需要最长子数组
            // 比如[2,2,0,0,0] 当然是4，1最好
            if (!map.containsKey(curSum))
                map.put(curSum, i);
        }
        return res;
    }
}

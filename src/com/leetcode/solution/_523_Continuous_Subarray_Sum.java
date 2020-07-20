package com.leetcode.solution;

import java.util.*;

class _523_Continuous_Subarray_Sum {
    // O(n), O(min(n,k))
    public boolean checkSubarraySum(int[] nums, int k) {
        /**
         在每个索引位置i, 计算当前和对k的mod值, 假设在索引x处, sum[0~x] = m*k + mod_x;
         在索引y处, sum[0~y] = n*k + mod_y; 如果mod_x == mod_y且y-x > 1说明sum[x~y]
         即为一个符合要求的连续子数组, 一旦出现新的mod值出现在map中, 判断索引差是否大于1.
         **/
        if (nums.length < 2)
            return false;
        // 1) 当nums中有连续0, 无论k为何值都是正确的;
        for (int i = 0; i < nums.length - 1; i++)
            if (nums[i] == 0 && nums[i + 1] == 0)
                return true;
        // 除情况1之外出现k为0都是错误的;
        if (k == 0)
            return false;
        // k为负数也是可能的, 但是要将其变为对应正数来求mod.
        if (k < 0)
            k = -k;

        //用map来保存每个mod值对应的索引
        Map<Integer, Integer> map = new HashMap<>();
        //解决到达某个i时sum恰好可以整除k的情况, 选择-1的原因是要求连续子数组长度大于等于2, 这样可以避免第一个数字为0的情况
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int mod = sum % k;

            if (map.containsKey(mod))
                if (i - map.get(mod) > 1)
                    return true;
                else
                    map.put(mod, i);// 不存在再更新
        }

        return false;
    }
}
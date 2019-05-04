package com.leetcode.solution;

import java.util.*;

/**
 * 3sum最接近K
 * <p>
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 */

public class _0163SumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                if (sum == target) return sum; //相等则返回
                if (Math.abs(sum - target) < Math.abs(result - target))
                    result = sum; //如果更接近则更新

                if (sum > target) //大于target则右指针左移
                    right--;
                else left++; //否则左指针右移
            }
        }
        return result;
    }
}

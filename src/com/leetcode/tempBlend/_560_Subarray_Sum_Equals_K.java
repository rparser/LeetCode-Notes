package com.leetcode.solution;

import java.util.*;

/**
 * 子数组和为K
 * <p>
 * Time complexity : O(n). The entire nums array is traversed only once.
 * Space complexity : O(n). Hashmap can contain upto nn distinct entries in the worst case.
 * 1loop，建map<当前sum，能达到这个sum的可能数>,把每个sum加入map，用containsKey(sum-k)得到result
 */

public class _560_SubarraySumEqualsK {
    //找到个数
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); //保存当前sum个数
        map.put(0, 1);
        int sum = 0, result = 0;
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) result += map.get(sum - k); //如果找到，个数加入集合
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
    // 正数负数通用
    public static int[] findSubArraySum(int[] nums, int sum) {
        //cur to keep track of cummulative sum till that point
        int cur = 0, left = -1, right = 0;
        // <current sum, start from>
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); //if first value is result
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            // if hashMap already has the value, means we already
            // have subarray with the sum - so stop
            //如果a3a4为结果，则a1a2a3a4-a1a2
            if (map.containsKey(cur - sum)) {
                // start from next value, copyOfRange is right exclusive so right + 1
                left = map.get(cur - sum) + 1;
                right = i + 1;
                break;
            }
            //if value is not present then add to hashmap
            map.put(cur, i);
        }
        // if left is -1 : means we have reached end without the sum
        if (left == -1)
            return new int[]{};
        else
            return Arrays.copyOfRange(nums, left, right);
    }

    // 只有正数找到例子,space O1
    public static int[] findSubArraySum(int[] nums, int sum) {
        int cur = 0, left = 0, right = 0;

        while (right < nums.length) {
            // If cur exceeds the sum, then remove the starting elements
            // 可以加上left < right - k保证至少为k大小的子集
            while (cur > sum) {
                cur -= nums[left];
                left++;
            }

            // If cur becomes equal to sum, then return true
            if (cur == sum)
                return Arrays.copyOfRange(nums, left, right);

            // Add this element to cur
            cur += nums[right];
            right++;
        }
        if (cur == sum) {
            return Arrays.copyOfRange(nums, left, nums.length);
        }
        return new int[]{};
    }
}
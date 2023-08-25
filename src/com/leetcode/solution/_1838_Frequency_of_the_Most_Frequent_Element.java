package com.leetcode.solution;

import java.util.Arrays;

/**
 * 首先很容易想到，先对数组进行排序。
 * 接下来，我们只需要找到某一段区间内，每个值与该区间内最后一个值相差的总和，不超过目标k的最大值。
 * O(nlogn) ~ O(logn)
 */
public class _1838_Frequency_of_the_Most_Frequent_Element {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int result = 1;
        int left = 0;
        int cost = 0;
        for (int right = 1; right < nums.length; right++){
            //计算区间内每个值，与区间内最后一个值相差的总和
            cost += (nums[right] - nums[right-1]) * (right - left);
            //如果超过目标值
            while (cost > k){
                //那么就减去区间内最左侧的值与最后一个值的差距。
                //然后再让区间左侧向右移动一位，相等于整个区间缩小了一位距离，在缩小的区间内再判断是否满足要求
                cost -= nums[right] - nums[left];
                left++;
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}

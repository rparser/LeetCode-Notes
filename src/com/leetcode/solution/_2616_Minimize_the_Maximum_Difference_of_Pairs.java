package com.leetcode.solution;

import java.util.Arrays;

/**
 * 排序+贪心+二分
 * <p>
 * 时间复杂度：O(nlogn+nlogU)，其中 nnn 为 nums\textit{nums}nums 的长度，
 * U=max⁡(nums)−min⁡(nums)U=\max(\textit{nums})-\min(\textit{nums})U=max(nums)−min(nums)。
 * 空间复杂度：O(1)。忽略排序时的栈空间，仅用到若干额外变量。
 */

public class _2616_Minimize_the_Maximum_Difference_of_Pairs {
    public int minimizeMax(int[] nums, int p) {
        // 排序
        Arrays.sort(nums);
        // 二分：最小0，最大数组差值的最值
        int left = 0;
        int right = nums[nums.length - 1] - nums[0];
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (judge(mid, nums, p)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean judge(int mid, int[] nums, int p) {
        // 遍历相邻两个是否满足小于等于mid，满足跳过2个，count++
        // 不满足 跳过1个
        int i = 0;
        int count = 0;
        while (i + 1 < nums.length) {
            if (nums[i + 1] - nums[i] <= mid) {
                count++;
                if (count == p) {
                    return true;
                }
                i += 2;
            } else {
                i++;
            }
        }
        return false;
    }
}


package com.leetcode.solution;

/**
 * 孤单整数
 * <p>
 * Time complexity: O(logn)
 * Space complexity: O(1)
 * while(l < r),m为中值，看m与对应n=m^1是否一致，判断问题在左侧l=m+1还是右侧r=m
 */

public class _540_SingleElementinaSortedArray {
    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length;
        if (r == 1) return nums[0];
        while (l < r) {
            int m = l + (r - l) / 2;
            // int n = m % 2 == 0 ? m + 1 : m - 1;
            int n = m ^ 1; //位运算,m为奇n为下一位，m为偶n为上一位
            if (n == r) return nums[r - 1]; //如果n已经越界（m为最后一个值）返回最后一个值
            if (nums[m] == nums[n]) //mn一致则是前面都正确
                l = m + 1; //问题在右侧
            else //前面出问题
                r = m; //问题在左侧
        }
        return nums[l];
    }
}

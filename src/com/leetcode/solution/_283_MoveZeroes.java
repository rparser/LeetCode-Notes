package com.leetcode.solution;

/**
 * 思路：定义一个 pos变量，要找到第一个插入0的位置。
 * loop array , 如果非0，overwrite nums[pos] = nums[i] , pos++
 * 第二次loop将[pos-end]补0
 * 关键字： insert position
 */

public class _283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int pos = 0;//the place to start insert 0
        for (int i = 0; i < nums.length; i++) {//reverse 0 at star: for(int i = nums.length-1;i > 0; i--)
            if (nums[i] != 0) {
                nums[pos] = nums[i];
                pos++;//increment position only if non-zero
            }
        }
        for (int i = pos; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

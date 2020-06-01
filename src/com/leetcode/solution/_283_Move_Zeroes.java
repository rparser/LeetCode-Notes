package com.leetcode.solution;

/**
 * 思路：定义一个 pos变量，要找到第一个插入0的位置。
 * loop array , 如果非0，overwrite nums[pos] = nums[i] , pos++
 * 第二次loop将[pos-end]补0
 * 关键字： insert position
 */
public class _283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        if (nums.length < 1) return;
        //pos为从哪个index开始后面变0（有几个非0的数）
        int pos = 0;
        for (int i = 0; i < nums.length; i++)
            //不等于0则此pos更新为相应值,pos也要增加
            if (nums[i] != 0) {
                nums[pos] = nums[i];
                pos++;
            }
        //后面填为0
        for (int i = pos; i < nums.length; i++) nums[i] = 0;
    }
}

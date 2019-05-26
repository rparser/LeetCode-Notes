package com.leetcode.solution;

/**
 Time complexity : O(n). We are doing a single pass through the nums array, hence nn steps, where nn is the length of array nums.

 Space complexity : O(1). We are not using any extra memory.
 */

public class _055_JumpGame {
    public boolean canJump(int[] nums) {
        int max = 0; //路过的位置之处再跳一次能到达的最远的位置
        for (int i = 0; i <= max; i++) {
            max = Math.max(max, i + nums[i]);
            if (max >= nums.length - 1) return true;
        }
        return false;
    }
}

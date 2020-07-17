package com.leetcode.solution;

/**
 * Time complexity : O(n). We are doing a single pass through the nums array, hence nn steps, where nn is the length of array nums.
 * Space complexity : O(1). We are not using any extra memory.
 */

public class _055_Jump_Game {
    //O(N), O(1)
    public boolean canJump(int[] nums) {
        int max = 0; //路过的位置之处再跳一次能到达的最远的位置
        //如果i已经超出最远值而max还未更新则返回false
        for (int i = 0; i <= max; i++) {
            //更新为当前index+此处能跳的距离
            max = Math.max(max, i + nums[i]);
            //到头立刻返回true
            if (max >= nums.length - 1)
                return true;
        }
        return false;
    }
}

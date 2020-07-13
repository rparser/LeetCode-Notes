package com.leetcode.solution;

class _169_majority_element {
    //候选人(cand_num)初始化为nums[0]，票数count初始化为1。
    //当遇到与cand_num相同的数，则票数count = count + 1，否则票数count = count - 1。
    //当票数count为0时，更换候选人，并将票数count重置为1。

    // O(n), O(1) Boyer–Moore majority vote algorithm
    //有可能的众数只可能是当前值或上一个candidate
    //每次i清零保证之前不存在众数
    public int majorityElement(int[] nums) {
        int can_num = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            //相同则加票
            if (can_num == nums[i])
                count++;
                //不同则减票
            else {
                count--;
                //如果减到了0更换候选人
                if (count == 0) {
                    can_num = nums[i];
                    count = 1;
                }
            }
        }
        return can_num;
    }
}

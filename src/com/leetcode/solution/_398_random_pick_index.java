package com.leetcode.solution;

import java.util.Random;

class _398_random_pick_index {
    private int[] nums;

    //假设当前正要读取第n个数据，则我们以1/n的概率留下该数据，否则留下前n-1个数据中的一个。
    public _398_random_pick_index(int[] nums) {
        this.nums = nums;
    }

    // O(N), O(N)
    public int pick(int target) {
        Random random = new Random();
        //统计有多少个
        int count = 0;
        //选去的index
        int index = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] == target) {
                count++;
                //我们以1/n的概率留下该数据,random.nextInt是[0,n)之间的随机正数，则其正好为0的概率即为1/n
                if (random.nextInt(count) == 0)
                    index = i;
            }

        return index;
    }
}
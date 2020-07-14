package com.leetcode.solution;

class _136_single_number {
    public int singleNumber(int[] nums) {
        int ans = 0;
        // 位运算，^Exclusive or自身为零
        for(int num: nums)
            ans ^= num;

        return ans;
    }
}
package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class _503_next_greater_element_ii {
    //循环数组 Monotone stack单调增
    public int[] nextGreaterElements(int[] nums) {
        // 单调增栈中保存的是索引
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);

        for (int i = 0; i < 2 * nums.length - 1; i++) {
            // 取模，实现循环数组
            int curIndex = i % nums.length;
            //如果当前比peek更大[1,1,1,1,1,6]最后的6可以把1都处理
            //处理stack里的元素，如果小的就pop出来
            while (!stack.isEmpty() && nums[curIndex] > nums[stack.peek()])
                res[stack.pop()] = nums[curIndex]; // 栈中保存的是索引


            stack.push(curIndex);
        }
        return res;
    }
}

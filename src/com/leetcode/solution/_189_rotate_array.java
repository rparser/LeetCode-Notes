package com.leetcode.solution;

import java.util.*;

class _189_rotate_array {
    //O(N), O(1)
    // 当我们旋转数组 k 次， k%n 个尾部元素会被移动到头部，剩下的元素会被向后移动。
    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        //完全反转,全送到头部
        reverse(nums, 0, nums.length - 1);
        //前半段反转（排序）
        reverse(nums, 0, k - 1);
        //后半段反转（排序）
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        int count = 0;//计数，统计交换的次数

        //第一层循环在 len % k == 0，时执行，
        for (int start = 0; count < len; start++) {
            int current = start;    //当前位置
            int prev = nums[start]; //交换的数字
            do {
                int next = (current + k) % len; //下一个位置
                //两个数字交换位置
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                //更新当前位置
                current = next;
                count++;
                // 下一层循环会跟len和k的Greatest common divisor有关,不为1需要多次遍历
            } while (start != current);
        }
    }
}

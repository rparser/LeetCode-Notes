package com.leetcode.solution;

/**
 * 颜色换顺序
 * 三指针
 * 3 pointer： 'i' point to the current position ,
 * 'left' point to the position right after 0, 'right' point to the position right before 2.
 */

public class _075_SortColors {
    public void sortColors(int[] nums) {
        int i = 0, left = 0, right = nums.length - 1;
        while (i <= right) { //遍历完一次
            if (nums[i] == 0) {
                swap(nums, i, left);
                left++; //left以左都为0
                i++;//left side is sorted already
            } else if (nums[i] == 2) {
                swap(nums, i, right); //right以右都为2
                right--;
            } else {
                i++;//skip 1
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void sortColors2pass(int[] nums) {
        int[] freq = new int[3];
        for (int i = 0; i < nums.length; i++) {
            freq[nums[i]] += 1;
        }
        int j = 0;//j is index in nums
        for (int i = 0; i < freq.length; i++) {
            int count = freq[i];
            while (count > 0) {
                nums[j] = i;
                j++;
                count--;
            }
        }
    }
}

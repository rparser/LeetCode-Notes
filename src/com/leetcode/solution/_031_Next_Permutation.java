package com.leetcode.solution;

/**
 * 字母序下一个排序 O(N)
 **/
public class _031_Next_Permutation {
    public void nextPermutation(int[] nums) {
        // [1,2,3]反转2,3 [1,2,3,4]反转4,3 所以要从右找到第一个升序的
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1])
            i--;
        if (i >= 0) { //i如果小于0则是完全逆序[4,3,2,1]
            int j = nums.length - 1;

            while (j >= 0 && nums[j] <= nums[i])
                j--;

            swap(nums, i, j);
        }
        reverse(nums, i + 1); //逆序直接翻转(从start开始)
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

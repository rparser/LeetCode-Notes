package com.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

class _442_find_all_duplicates_in_an_array {
    // 出现过的数字的index变负数,本题是1 ~ N
    // 不需额外空间, 如果有负数可以变为-N-n
    //O(N), O(1)
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            // 1~n 从1开始所以需要-1否则不需要-1, +1
            int index = Math.abs(nums[i]) - 1;
            // 如果负数则证明此index出现过，加入结果
            if (nums[index] < 0)
                res.add(Math.abs(index + 1));
            // 把数字变为负数/正数
            nums[index] = -nums[index];
        }
        return res;
    }

    // Cyclic sort swap法
    public static List<Integer> findNumbers(int[] nums) {
        int i = 0;

        while (i < nums.length)
            if (nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;

        List<Integer> duplicateNumbers = new ArrayList<>();

        for (i = 0; i < nums.length; i++)
            if (nums[i] != i + 1)
                duplicateNumbers.add(nums[i]);

        return duplicateNumbers;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

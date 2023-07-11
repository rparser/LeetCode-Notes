package com.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

public class _448_find_all_numbers_disappeared_in_an_array {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        //第一遍扫描，根据数组的值找到对应的下标，比如3对应下标2
        //将arr[2]设置成负数
        for (int i = 0; i < nums.length; ++i) {
            // 如果已经为负数则变为正数
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0)
                nums[index] *= -1;
        }
        //第二遍扫描，找到所有非负数，非负数所在的下标+1，即为缺失的数字
        for (int i = 0; i < nums.length; ++i)
            if (nums[i] > 0)
                res.add(i + 1);

        return res;
    }

    //排序法
    public static List<Integer> findNumbersSwap(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            // 如果此nums[i]位置不对则swap，位置对则不动
            if (nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }

        List<Integer> missingNumbers = new ArrayList<>();
        for (i = 0; i < nums.length; i++)
            if (nums[i] != i + 1)
                missingNumbers.add(i + 1);

        return missingNumbers;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

package com.leetcode.solution;

/**
 * 思路: Two pointers. i start from 0, j start from len-1, add two as sum and compared to sum
 * If smaller, i++, larger j--, else found
 * Complexity O(N)
 * <p>
 * 3sum: i -start j-end, k-i+1
 * i need to skip duplicates
 * if get 0 j and k skip duplicates, j-- &k++, seek for next answer where i fixed
 */

public class _167_Two_Sum_II_Input_Array_is_Sorted {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int len = numbers.length;
        int i = 0, j = len - 1; //一个从头一个从尾
        while (i < len && j > 0) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                break;
            }
            if (sum < target) {
                i++;
            } //小于则左指针右移
            else {
                j--;
            } //大于则右指针左移
        }
        res[0] = i + 1; //仅是这个题目（index从1开始），正常不需要+1
        res[1] = j + 1;
        return res;
    }
}

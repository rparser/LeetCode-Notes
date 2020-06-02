package com.leetcode.solution;

/**
 * 思路：就是MergeSort的Merge part，从后往前对比num1,num2的末位，加入到num1数组的末位
 * 关键字：Merge Sort
 * <p>
 * Time complexity : O(n+m).
 * Space complexity : O(1).
 */

public class _088_MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 || j >= 0) {
            //先处理两个边界条件，i<0以及j<0，这表示一个数组已经拷贝完了
            if (i < 0) nums1[k--] = nums2[j--];
            else if (j < 0) nums1[k--] = nums1[i--];
                //反向比较时，拷贝的是较大的那个元素
            else if (nums1[i] <= nums2[j]) nums1[k--] = nums2[j--];
            else nums1[k--] = nums1[i--];
        }
    }
}

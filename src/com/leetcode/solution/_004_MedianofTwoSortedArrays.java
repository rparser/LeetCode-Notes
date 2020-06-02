package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

/**
 * 求两个已排序数组的中位数
 * <p>
 * Time complexity: O(log(min(m,n))). Binary Search
 * At first, the searching range is [0, m][0,m]. And the length of this searching range will be reduced by half after each loop.
 * So, we only need log(m) loops.
 * Since we do constant operations in each loop, so the time complexity is O(log(m)).
 * Since m≤n, so the time complexity is O(log(min(m,n))).
 * <p>
 * Space complexity: O(1).
 * We only need constant memory to store 99 local variables, so the space complexity is O(1).
 * <p>
 * https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2471/very-concise-ologminmn-iterative-solution-with-detailed-explanation
 **/

/**
 * 1. 给定两个数组a[], b[], 从数组a[]中找到中位数M。把a[], b[]切分成两部分。
 * a1[] 小于M, a2[]大于等于M
 * b1[] 小于M, b2[]大于等于M
 * 两个数组相当于被切分成了两个group.
 * groupA = {a1[], b1[]}
 * groupB = {a2[], b2[]}
 * 2. 如果k大于len(groupA)，那么要找的元素肯定在groupB. 此时k -= len(groupA)
 * 如果k小于等于len(groupA)，那么要找到的元素肯定在groupA.
 * 3. 回步骤1
 **/

public class _004_MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2) return findMedianSortedArrays(nums2, nums1); //默认为nums1数组不大于nums2数组

        int k = (n1 + n2 + 1) / 2; // ie, n1=2 , n2 = 3, n1+n2+1=6(even) , k = 3 ; n1=2, n2=2, n1+n2+1=5, k=5/2=2 (left of mid)
        // k是个数，偶数k-1为左中位数，奇数k为正中位数
        int left = 0;
        int right = n1;

        while (left < right) {
            int mid1 = left + (right - left) / 2;
            int mid2 = k - mid1;
            if (nums1[mid1] < nums2[mid2 - 1]) left = mid1 + 1;
            else right = mid1;
        }

        int m1 = left;
        int m2 = k - left;
        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
                m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);

        if ((n1 + n2) % 2 == 1)  //为奇数时，则直接返回
            return c1;

        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
                m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
        return (c1 + c2) * 0.5; //为偶数时 求中值和中值下一个值
    }

    public double findMedianSortedArraysIterative(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m < n) return findMedianSortedArraysIterative(nums2, nums1);
        if (n == 0) return (nums1[(m - 1) / 2] + nums1[m / 2]) / 2.0;
        int left = 0, right = 2 * n;
        while (left <= right) { // Try Cut 2
            int mid2 = (left + right) / 2; // Calculate Cut 1 accordingly
            int mid1 = m + n - mid2;
            double L1 = mid1 == 0 ? Double.MIN_VALUE : nums1[(mid1 - 1) / 2]; // Get L1, R1, L2, R2 respectively
            double L2 = mid2 == 0 ? Double.MIN_VALUE : nums2[(mid2 - 1) / 2];
            double R1 = mid1 == m * 2 ? Double.MAX_VALUE : nums1[mid1 / 2];
            double R2 = mid2 == n * 2 ? Double.MAX_VALUE : nums2[mid2 / 2];
            if (L1 > R2) left = mid2 + 1; // A1's lower half is too big; need to move C1 left (C2 right)
            else if (L2 > R1) right = mid2 - 1; // A2's lower half too big; need to move C2 left.
            else return (Math.max(L1, L2) + Math.min(R1, R2)) / 2; // Otherwise, that's the right cut.
        }
        return -1;
    }


    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._004_MedianofTwoSortedArrays");
    }

    @Test
    public void testSolution() {
//        int[] input=new int[]{0,1,5,8,10};
//        int[] input2=new int[]{2,5,6,7,12,13};
//        Assert.assertEquals(6,findMedianSortedArrays(input,input2),0.1);
        int[] input = new int[]{1, 3, 5, 7};
        int[] input2 = new int[]{2, 4, 6, 8, 9, 10};
        Assert.assertEquals(5.5, findMedianSortedArrays(input, input2), 0.1);
    }
}

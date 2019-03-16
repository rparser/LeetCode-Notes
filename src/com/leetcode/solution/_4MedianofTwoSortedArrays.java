package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

/**Time complexity: O(log(min(m,n))).
At first, the searching range is [0, m][0,m]. And the length of this searching range will be reduced by half after each loop.
 So, we only need log(m) loops.
 Since we do constant operations in each loop, so the time complexity is O(log(m)).
 Since m≤n, so the time complexity is O(log(min(m,n))).

Space complexity: O(1).
We only need constant memory to store 99 local variables, so the space complexity is O(1).
 **/

public class _4MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1); //默认为nums1数组小于nums2数组

        int k = (n1 + n2 + 1) / 2;
        int left = 0;
        int right = n1;

        while (left < right) {
            int m1 = left + (right - left) / 2;
            int m2 = k - m1;
            if (nums1[m1] < nums2[m2 - 1])
                left = m1 + 1;
            else
                right = m1;
        }

        int m1 = left;
        int m2 = k - left;

        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
                m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);

        if ((n1 + n2) % 2 == 1)
            return c1;

        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
                m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);

        return (c1 + c2) * 0.5;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._4MedianofTwoSortedArrays");
    }
    @Test
    public void testSolution() {
//        int[][] input=new int[][]{{0,0,0},{0,1,0},{0,0,0}};
//        int[][] input2=new int[][]{{0,1,2},{3,4,5},{6,7,8}};
//        Assert.assertEquals(0,minPathSum(input));
//        Assert.assertEquals(16,minPathSum(input2));
    }
}

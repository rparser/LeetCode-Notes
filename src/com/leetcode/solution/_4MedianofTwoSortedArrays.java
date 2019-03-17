package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

/**
 * Time complexity: O(log(min(m,n))). Binary Search
 * At first, the searching range is [0, m][0,m]. And the length of this searching range will be reduced by half after each loop.
 * So, we only need log(m) loops.
 * Since we do constant operations in each loop, so the time complexity is O(log(m)).
 * Since m≤n, so the time complexity is O(log(min(m,n))).
 * <p>
 * Space complexity: O(1).
 * We only need constant memory to store 99 local variables, so the space complexity is O(1).
 **/

/**
 * 例子nums1= {1，3，5，7}; nums2={2,4,6,8,9,10} 答案应为 (5+6)/2=5.5 , 一共10个数所以要取出第5个数，取和第6个数的平均值
 * 思路：取出两个中值比较5和6，由于5<6，所以5之前（1,3）不可能是中值，逻辑为：需要加入足够多的数字进入总数组的前半部分，而nums1偏小，
 * 则偏小的数组（nums1）需要取更多的前半部值（即可能需要取到7），则前半部分值的已有值均会被取到放入总数组前半部分，所以均不会为中值
 * 同理，偏大的数组（nums2）后半部分一定会在总数组后半部分，所以会被抛弃
 * 经第一轮计算后，nums1={5,7} nums2={2,4,6} 虽然0,1,2比3小（完全排序merge后会在3,4之前），但3,4被抛弃，因为中值5小
 * 10,11比7,8,9大（完全排序后10,11会在7,8,9后），但7,8,9被抛弃，因为6中值大
 * <p>
 * 再计算一轮 7 vs 4 nums1={5,10} nums2={2,6}
 * (5+6)/2=5.5
 **/

public class _4MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1); //默认为nums1数组不大于nums2数组

        int k = (n1 + n2 + 1) / 2; // ie, n1=2 , n2 = 3, n1+n2+1=6(even) , k = 3 ; n1=2, n2=2, n1+n2+1=5, k=5/2=2 (left of mid)
        System.out.println("k= " + k);
        int left = 0;
        int right = n1;

        while (left < right) {
            int m1 = left + (right - left) / 2;
            System.out.println("firstm1= " + m1);
            int m2 = k - m1;
            System.out.println("firstm2= " + m2);
            if (nums1[m1] < nums2[m2 - 1]) {
                System.out.println("left= " + left);
                System.out.println("nums1[m1]= " + nums1[m1]);
                System.out.println("nums2[m2 - 1]= " + nums2[m2 - 1]);
                left = m1 + 1;
                System.out.println("newest left= " + left);
                System.out.println("newest right= " + right);
            } else {
                System.out.println("right= " + right);
                System.out.println("nums1[m1]= " + nums1[m1]);
                System.out.println("nums2[m2 - 1]= " + nums2[m2 - 1]);
                right = m1;
                System.out.println("newest left= " + left);
                System.out.println("newest right= " + right);
            }
        }

        int m1 = left;
        int m2 = k - left;

        System.out.println("m1= " + m1);
        System.out.println("m2= " + m2);

        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
                m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);

        if ((n1 + n2) % 2 == 1)  //为奇数时，则直接返回
            return c1;

        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
                m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);

        return (c1 + c2) * 0.5; //为偶数时 求中值和中值下一个值
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._4MedianofTwoSortedArrays");
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

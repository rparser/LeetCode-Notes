package com.leetcode.solution;

class _628_Maximum_Product_of_Three_Numbers {
    public int maximumProduct(int[] nums) {
        //由于可能出现负数，因此还需要考虑最小的两个数
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3)
                max3 = num;
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return Math.max(max3 * max2 * max1, max1 * min1 * min2);
    }
}

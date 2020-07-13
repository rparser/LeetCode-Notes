package com.leetcode.solution;

public class _191_number_of_1_bits {
    // O(1) , O(1)
    public int hammingWeight(int n) {
        int count = 0;
        //在二进制表示中，数字 n 中最低位的 1 总是对应 n - 1中的 0 。
        //因此，将 n 和 n−1 与运算总是能把 n 中最低位的 1 变成 0 ，并保持其他位不变。
        // (n)100 & (n-1)011 = 000
        while (n != 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }
}

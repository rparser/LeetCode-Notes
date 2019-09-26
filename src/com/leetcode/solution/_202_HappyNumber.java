package com.leetcode.solution;

public class _202_HappyNumber {
    public boolean isHappy(int n) {
        int[] mark = new int[8];
        while (n > 1) {
            n = convert(n);
            if (n < 243) {
                int sec = n >> 5;
                int mask = 1 << (n & 0x1f);
                if ((mark[sec] & mask) > 0) return false;
                mark[sec] |= mask;
            }
        }
        return true;
    }

    private int convert(int n) {
        int sum = 0;
        while (n > 0) {
            int t = n % 10;
            sum += t * t;
            n /= 10;
        }
        return sum;
    }
}

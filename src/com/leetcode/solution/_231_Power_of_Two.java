package com.leetcode.solution;

class _231_Power_of_Two {
    public boolean isPowerOfTwo(int n) {
        if (n == 0)
            return false;

        return ((long) n & (-(long) n)) == (long) n;
    }

    public boolean isPowerOfTwo2(int n) {
        if (n == 0)
            return false;

        return ((long) n & ((long) n - 1)) == 0;
    }
}

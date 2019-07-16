package com.leetcode.solution;

/**
 * Time complexity: O(n)
 * Space complexity: O(1)
 */

public class _509_FibonacciNumber {
    public int fib(int N) {
        if (N <= 1) return N;
        int a = 0, b = 1;

        while (N-- > 1) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
}

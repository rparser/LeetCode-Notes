package com.leetcode.solution;

/**
 * O(sqrt(n))
 */
public class _1492_The_kth_Factor_of_n {
    public int kthFactor(int n, int k) {
        int count = 0, factor;
        for (factor = 1; factor * factor <= n; ++factor) {
            if (n % factor == 0) {
                count++;
                if (count == k) {
                    return factor;
                }
            }
        }
        factor--;
        if (factor * factor == n) {
            factor--;
        }
        for (; factor > 0; factor--) {
            if (n % factor == 0) {
                count++;
                if (count == k) {
                    return n / factor;
                }
            }
        }
        return -1;
    }
}

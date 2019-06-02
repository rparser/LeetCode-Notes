package com.leetcode.solution;

import java.util.Arrays;

/**
 * 质数个数
 * Time Complexity:O(nloglogn)
 * <p>
 * Space Complexity:O(n)
 * bool[]全设为T,2loop(2:n)(2*i:n:j+i),为T则res+否则continue,内层loop,差值i直到n变为F，返回res
 */

public class _204_CountPrimes {
    public int countPrimes(int n) {
        int result = 0;
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        if (n > 0) isPrime[1] = false; //0和1设成false其他为true
        for (int i = 2; i < n; i++) {
            if (!isPrime[i]) continue;
            result++; //只有此值为合数，才能结果++
            for (int j = 2 * i; j < n; j = j + i) //以i为差值更新
                isPrime[j] = false;
        }
        return result;
    }
}

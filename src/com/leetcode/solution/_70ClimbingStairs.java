package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

public class _70ClimbingStairs {
    public int climbStairs(int n) {
        int[] A = new int[n]; //用来记录到每一步有多少种走法
        A[0] = 1;
        if (n == 1)
            return 1;
        A[1] = 2;
        for (int i = 2; i < n; i++) {
            A[i] = A[i - 1] + A[i - 2];
        }
        return A[n - 1];
    }

    public int climbStairs3Steps(int n) { //三步走法
        int[] A = new int[n];
        A[0] = 1;
        if (n == 1)
            return 1;
        A[1] = 2;
        A[2] = 4;
        for (int i = 3; i < n; i++) {
            A[i] = A[i - 1] + A[i - 2] + A[i - 3]; //增加i-3
            System.out.println("i= " + i + "    " + A[i]);
        }
        return A[n - 1];
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._70ClimbingStairs");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(2, climbStairs(2));
        Assert.assertEquals(13, climbStairs3Steps(5));
    }
}

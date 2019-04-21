package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

/**
 * 动态规划，走楼梯问题，包括两步三步
 * <p>
 * Time complexity :O(n). Single loop upto n.
 * <p>
 * Space complexity :O(n). dp array of size n is used.
 */

public class _070ClimbingStairs {
    public int climbStairs(int n) {
        int[] result = new int[n]; //用来记录到每一步有多少种走法
        result[0] = 1;
        if (n == 1)
            return 1;
        result[1] = 2;
        for (int i = 2; i < n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n - 1];
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

    /**
     * {1,2,1,5,3} 数字转换成字母，1,2,1可以为AU或LA或ABA一共多少可能
     */

    public int climbStairs2(int[] dict) {
        int n = dict.length;
        int[] A = new int[n];
        A[0] = 1;
        if (n == 1)
            return 1;
        if (Integer.parseInt(dict[0] + Integer.toString(dict[1])) < 26)
            A[1] = 2;
        else A[1] = 1;
        for (int i = 2; i < n; i++) {
            if (Integer.parseInt((dict[i - 1]) + Integer.toString(dict[i])) < 26)
                A[i] = A[i - 2] + A[i - 1];
            else A[i] = A[i - 1];
        }
        System.out.println(A[n - 1]);
        return A[n - 1];
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._070ClimbingStairs");
    }

    @Test
    public void testSolution() {
        int[] dict = {1, 2, 2, 1};
        int[] dict2 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        Assert.assertEquals(2, climbStairs(2));
        Assert.assertEquals(13, climbStairs3Steps(5));
        Assert.assertEquals(5, climbStairs2(dict));
        Assert.assertEquals(14930352, climbStairs2(dict2));
    }
}

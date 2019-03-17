package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

public class _8StringtoInteger {
    public int myAtoi(String str) {

        if (str == null || str.length() < 1)
            return 0;

        // trim white spaces
        str = str.trim();

        boolean flag = true;

        // check negative or positive
        int i = 0;
        if (str.charAt(0) == '-') {
            flag = false;
            i++;
        } else if (str.charAt(0) == '+') {
            flag = true;
            i++;
        }
        // use double to store result
        double result = 0;

        // calculate value
        while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }

        if (flag == false)
            result = -result;

        // handle max and min
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        return (int) result;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._8StringtoInteger");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(4193, myAtoi("4193 with words"));
    }
}

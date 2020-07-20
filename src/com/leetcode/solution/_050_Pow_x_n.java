package com.leetcode.solution;

class _050_Pow_x_n {
    // O(logN), O(1)
    // 比如77的二进制(1001101)2->1的位数分别为1，4，8 和 64，只有这些位才加入乘积（为1的个数）
    public double myPow(double x, int n) {
        // 看n是正数还是负数
        boolean flag = n >= 0;
        double result = 1.0;
        while (n != 0) {
            // n & 1 是看最后一位是否为1，例如10是1010不为1
            if ((n & 1) != 0) {
                //如果是1加入结果，否则跳过直接自身平方
                result *= x;
            }
            x = x * x;
            n = n / 2;
        }
        //负数则为1/result
        return flag ? result : 1 / result;
    }
}
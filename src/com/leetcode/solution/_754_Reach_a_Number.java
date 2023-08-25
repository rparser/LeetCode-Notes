package com.leetcode.solution;

public class _754_Reach_a_Number {
    public int reachNumber(int target) {
        int result = 0, num = 0, t = Math.abs(target); // 由于target有负数情况，为了统一计算逻辑，所以取绝对值
        // 直到num值大于等于t，并且num减t是偶数，才结束while循环
        while (num < t || (num - t) % 2 != 0) {
            num += ++result;
        } // num=1+2+3+4+……
        return result;
    }

    public int reachNumberO1(int target) {
        target = Math.abs(target);
        int n = (int) Math.ceil((-1 + Math.sqrt(8L * target + 1)) / 2); // 注意 8*target 会超过 int 范围
        return (n * (n + 1) / 2 - target) % 2 == 0 ? n : n + 1 + n % 2;
    }
}

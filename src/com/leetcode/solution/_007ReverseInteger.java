package com.leetcode.solution;

/**
 * 翻转整数
 * <p>
 * Int 范围 -2,147,483,648 to 2,147,483,647 (−2^31,  2^31 − 1)
 * <p>
 * Time Complexity: O(log(x)). There are roughly log_{10}(x) digits in x.
 * Space Complexity: O(1).
 */

public class _007ReverseInteger {
    public int reverse(int x) {
        int rev = 0; // 翻转后的数字
        while (x != 0) { // 整数部分不等于0，即剩余值大于10
            int newRev = rev * 10 + x % 10; //已转换好的部分
            if ((newRev - x % 10) / 10 != rev) return 0; //逆操作验证是否overflow
            rev = newRev;
            x /= 10;
        }
        return rev;
    }
}

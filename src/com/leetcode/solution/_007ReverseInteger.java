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
            int pop = x % 10; // 求当前整数的余数
            x /= 10; // 求整数部分
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0; // 如果大于最大值
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0; // 或小于最小值返回0
            rev = rev * 10 + pop;
        }
        return rev;
    }
}

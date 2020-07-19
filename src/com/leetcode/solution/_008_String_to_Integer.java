package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

/**
 * 字符串变成整数
 * <p>
 * 先去掉空格，再检查是否空值，再检查正负号，只得到数字（去掉字母）
 */

public class _008_String_to_Integer {
    //O(N), O(1)
    public int myAtoi(String str) {
        // 检查空格
        str = str.trim();

        // 检查null
        if (str.length() < 1)
            return 0;

        // 检查正负号
        boolean flag = true;
        int i = 0;
        if (str.charAt(0) == '-') {
            flag = false;
            i++;
        } else if (str.charAt(0) == '+')
            i++;

        // 保存结果
        double result = 0;
        while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') { // 去掉非数字
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }

        if (!flag)
            result = -result;

        // 检查取值范围
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        return (int) result;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._008StringtoInteger");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(4193, myAtoi("4193 with words"));
    }
}

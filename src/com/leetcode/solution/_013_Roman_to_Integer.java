package com.leetcode.solution;

/**
 * 罗马变数字
 * 012数字变罗马 273数字变英文
 */

public class _013_Roman_to_Integer {
    public int romanToInt(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int result = 0;
        //存在4，9的时候，减去两个当前值
        if (s.contains("CM"))
            result -= 200;
        if (s.contains("CD"))
            result -= 200;
        // 110变90
        if (s.contains("XC"))
            result -= 20;
        // 60变40
        if (s.contains("XL"))
            result -= 20;
        if (s.contains("IX"))
            result -= 2;
        if (s.contains("IV"))
            result -= 2;

        for (char c : s.toCharArray())
            switch (c) {
                case 'M' -> result += 1000;
                case 'D' -> result += 500;
                case 'C' -> result += 100;
                case 'L' -> result += 50;
                case 'X' -> result += 10;
                case 'V' -> result += 5;
                case 'I' -> result += 1;
            }
        return result;
    }
}

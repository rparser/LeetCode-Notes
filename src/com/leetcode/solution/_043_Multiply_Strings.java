package com.leetcode.solution;

/**
 * 思路：  conclude that
 * `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]` //1st pos: inc, 2nd: sum + mul
 * using int[] to add up results in double for loop.O(mn)
 */

public class _043_MultiplyStrings {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int carry = i + j, curP = i + j + 1; //curP为该乘积所在的位置,carry为进位
                int sum = pos[curP] + mul; //当前sum为当前位已有值+当前乘积
                pos[carry] += sum / 10; //进位值
                pos[curP] = sum % 10; //该位置值
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i : pos)
            if (!(sb.length() == 0 && i == 0))
                sb.append(i); //avoid "00"

        return sb.length() == 0 ? "0" : sb.toString();
    }
}

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
                int p1 = i + j, p2 = i + j + 1;
                int sum = pos[p2] + mul;
                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m + n; i++)
            if (!(sb.length() == 0 && pos[i] == 0)) sb.append(pos[i]); //avoid "00"
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
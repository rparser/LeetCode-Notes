package com.leetcode.solution;

/**
 * 数字到英文
 * Time complexity : O(N). Intuitively the output is proportional to the number N of digits in the input.
 * Space complexity : O(1) since the output is just a string.
 */

public class _273IntegertoEnglishWords {
    private final String[] UNDER20 = {"One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen"};
    private final String[] UNDER100 = {"Twenty", "Thirty", "Forty", "Fifty",
            "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] HTMB = {"Hundred", "Thousand", "Million", "Billion"};
    private final int[] HTMBINT = {100, 1000, 1000 * 1000, 1000 * 1000 * 1000};

    public String numberToWords(int n) {
        if (n == 0) return "Zero";
        return convert(n).trim();
    }

    private String convert(int n) {
        if (n == 0) return "";
        if (n < 20) return " " + UNDER20[n - 1]; // 1 - 19
        if (n < 100) return " " + UNDER100[n / 10 - 2] + convert(n % 10); // 20 ~ 99
        for (int i = 3; i >= 0; --i)
            if (n >= HTMBINT[i]) // 从后往前看是不是大于
                return convert(n / HTMBINT[i]) + " " + HTMB[i] + convert(n % HTMBINT[i]); //算掉HTMB
        return "";
    }
}

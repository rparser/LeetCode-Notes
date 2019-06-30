package com.leetcode.solution;

/**
 * 思路: String相加的解法，如进位（inc =1），不然s设回0，sb加的值要%2, i j condition
 * 注意点： numA = charA -'0' （char转成int）
 */

public class _067_AddBinary {
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1;
        StringBuilder res = new StringBuilder();
        int inc = 0;
        while (i >= 0 || j >= 0) {
            int sum = inc;
            inc = 0;//set back inc
            if (i >= 0) sum += a.charAt(i) - '0';
            if (j >= 0) sum += b.charAt(j) - '0';
            i--;
            j--;
            if (sum >= 2) inc = 1;
            res.append(sum % 2);//only have 3 2 1 0
        }
        if (inc == 1) res.append(inc);
        return res.reverse().toString();
    }
}

package com.leetcode.solution;

/**
 * 思路: String相加的解法，如进位（inc =1），不然s设回0，sb加的值要%2, i j condition
 * 注意点： numA = charA -'0' （char转成int）
 */

public class _067_Add_Binary {
    public String addBinary(String a, String b) {
        //从结尾开始
        int i = a.length() - 1, j = b.length() - 1;
        StringBuilder res = new StringBuilder();
        int carry = 0; // 进位
        while (i >= 0 || j >= 0) {
            int sum = carry;
            carry = 0; //carry值已经取得，变为0

            if (i >= 0)
                sum += a.charAt(i) - '0';
            if (j >= 0)
                sum += b.charAt(j) - '0';

            i--;
            j--;
            // sum可能为3，有carry且都为1
            if (sum == 2 || sum ==3)
                carry = 1;

            res.append(sum % 2); // 0或2是0（2有carry）；1或3是1（3有carry）
        }
        //加上最后一位
        if (carry == 1)
            res.append(carry);

        return res.reverse().toString();
    }
}

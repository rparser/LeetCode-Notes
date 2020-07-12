package com.leetcode.solution;

class _415_add_strings {
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder("");
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int tmp = n1 + n2 + carry;

            carry = tmp / 10;
            res.append(tmp % 10);
            i--;
            j--;
        }
        if (carry == 1)
            res.append(1);

        return res.reverse().toString();
    }

    public static String addStringsFloats(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        // 先添加上小数点
        if (!num1.contains(".")) num1 = num1 + ".";
        if (!num2.contains(".")) num2 = num2 + ".";
        //取得小数部分 低吸猫decimal 的长度
        int decimal1 = num1.length() - num1.indexOf(".");
        int decimal2 = num2.length() - num2.indexOf(".");
        int maxDecimal = Math.max(decimal1, decimal2);
        // 最后一位要补齐长度
        int i = num1.length() - 1 + maxDecimal - decimal1;
        int j = num2.length() - 1 + maxDecimal - decimal2;
        int carry = 0;

        while (i >= 0 || j >= 0) {
            int n1 = 0, n2 = 0;
            if (i >= 0 && i <= num1.length() - 1) {
                //如果num1是.则num2也一定是.则直接跳过
                if (num1.charAt(i) == '.') {
                    i--;
                    j--;
                    res.append(".");
                    continue;
                } else
                    n1 = num1.charAt(i) - '0';
            }
            if (j >= 0 && j <= num2.length() - 1)
                n2 = num2.charAt(j) - '0';

            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            res.append(tmp % 10);
            i--;
            j--;
        }
        if (carry == 1)
            res.append(1);
        // 可能结果会出现123. 但这最后是dot是合法的，当然了也可以如果最后一位是0则直接消去，或补上0
        if (res.charAt(0) == '.') {
            // 补上0
            res.insert(0, "0");
            // 消去dot.
            // res.deleteCharAt(0);
        }
        return res.reverse().toString();
    }
}
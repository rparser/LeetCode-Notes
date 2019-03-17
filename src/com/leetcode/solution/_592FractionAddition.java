package com.leetcode.solution;

import java.util.stream.*;

import org.junit.*;
import org.junit.runner.*;

public class _592FractionAddition {
    public String fractionAddition(String expression) {
        String[] fracs = expression.split("(?=[-,+])"); // splits input string into individual fractions
        String res = "0/1";
        for (String frac : fracs) {// add all fractions together
            res = add(res, frac);
//            System.out.println(frac);
        }
        return res;
    }

    public String add(String frac1, String frac2) {
        int[] f1 = Stream.of(frac1.split("/")).mapToInt(Integer::parseInt).toArray(),
                f2 = Stream.of(frac2.split("/")).mapToInt(Integer::parseInt).toArray();
//        System.out.println(f1[0]+"f1");
//        System.out.println(f2[0]+"f2");
        int numer = f1[0] * f2[1] + f1[1] * f2[0], denom = f1[1] * f2[1]; //Numerator分子 and denominator分母
        String sign = ""; //正负数标志
        if (numer < 0) sign = "-";
        numer *= -1; //如果为负数
        return sign + numer / gcd(numer, denom) + "/" + denom / gcd(numer, denom); // 返回字符串
    }

    // Computes gcd using Euclidean(U-cli-deen) algorithm, Greatest common divisor 最大公约数
    public int gcd(int x, int y) {
        return x == 0 || y == 0 ? x + y : gcd(y, x % y);
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._592FractionAddition");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(
                "0/1", fractionAddition("-1/2+1/2"));
    }
}

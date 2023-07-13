package com.leetcode.common;

public class test {
    public static int holes(int num) {
        String str = Integer.toString(num);
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '8') count += 2;
            else if (c == '0' || c == '4' || c == '6' || c == '9') count++;
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(holes(1288));
    }
}

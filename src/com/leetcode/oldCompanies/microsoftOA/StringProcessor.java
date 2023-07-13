package com.leetcode.oldCompanies.microsoftOA;

import java.util.Arrays;
import java.util.Collections;

public class StringProcessor {
    public static void main(String[] args) {
        System.out.println("Hello World!");
//        System.out.println(no3char("uuuttxaaa"));
//        System.out.println(no3char("aaa"));
//        System.out.println(no3char("abbbbcc"));
//        System.out.println(replaceQ("a?b?b?ac?"));
        System.out.println(replaceQ("???????"));
//        System.out.println(no3du("aaabbbccc"));
        System.out.println(minduct("aaaaabbcdddddeeefff"));
    }

    public static String no3char(String s) {
        if (s.length() < 3) return s;
        int curStart = 0, start = 0, end = 1, max = 0, curEnd = 2;
        while (curEnd < s.length()) {
            if (s.charAt(curEnd - 1) != s.charAt(curEnd) || s.charAt(curEnd) != s.charAt(curEnd - 2)) {
                if (curEnd - curStart + 1 > max) {
                    end = curEnd;
                    start = curStart;
                    max = curEnd - curStart + 1;
                }
            } else
                curStart = curEnd - 1;
            curEnd++;
        }
        return s.substring(start, end + 1);
    }

    public static String replaceQ(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '?') { //when it's '?'
                if (i == 0 && s.length() == 1) sb.append('a');
                else if (i == 0) {
                    if (s.charAt(i + 1) == 'a')
                        sb.append('b');
                    else
                        sb.append('a');
                } else if (i == s.length() - 1) {
                    if (s.charAt(i - 1) == 'a')
                        sb.append('b');
                    else
                        sb.append('a');
                } else {
                    if (s.charAt(i - 1) != s.charAt(i + 1)) {
                        if (s.charAt(i - 1) != 'a' && s.charAt(i + 1) != 'a') sb.append('a');
                        else if (s.charAt(i - 1) != 'b' && s.charAt(i + 1) != 'b') sb.append('b');
                        else sb.append('c');
                    } else {
                        if (s.charAt(i - 1) == 'a')
                            sb.append('b');
                        else
                            sb.append('a');
                    }
                }
            } else sb.append(s.charAt(i));
            i++;
        }
        return sb.toString();
    }

    public static String no3du(String s) {
        if (s.length() < 3) return s;
        StringBuilder sb = new StringBuilder(s.substring(0, 2));
        int i = 2;
        while (i < s.length()) {
            if (s.charAt(i) != s.charAt(i - 1) || s.charAt(i) != s.charAt(i - 2)) sb.append(s.charAt(i));
            i++;
        }
        return sb.toString();
    }

    public static int minduct(String s) {
        Integer[] count = new Integer[26];
        Arrays.fill(count, 0);
        for (char c : s.toCharArray())
            count[c - 'a']++;
        int deduct = 0;
        int i = 0;
        Arrays.sort(count, Collections.reverseOrder());
        while (i < 26) {
            int j = i + 1;
            while (j < 26 && count[j] > 0 && count[i] > 0) {
                if (count[j].equals(count[i])) {
                    deduct++;
                    count[i]--;
                }
                j++;
            }
            i++;
        }
        return deduct;
    }
}

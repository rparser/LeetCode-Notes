package com.leetcode.solution;

import java.util.Arrays;

/**
 * 思路：use two pointer, i from start j from end, convert char to uppercase & watch cases symbol,
 * 关键词： char的操作(Character.toUpperCase & Character.isLetterOrDigit(c))
 * Time & Space complexity: O(n) time O(1) space
 */

public class _125_Valid_Palindrome {
    public static boolean isPalindrome(String s) {
        s = s.trim();
        int i = 0, j = s.length() - 1;
        while (i < s.length()) //先去掉头尾的符号
            if (!Character.isLetterOrDigit(s.charAt(i))) i++;
            else break;
        while (j > 0)
            if (!Character.isLetterOrDigit(s.charAt(j))) j--;
            else break;
        while (i < j) {
            if (Character.toUpperCase(s.charAt(i)) != Character.toUpperCase(s.charAt(j))) return false;
            i++;
            while (i < s.length()) //如果不是则继续向前
                if (!Character.isLetterOrDigit(s.charAt(i))) i++;
                else break;
            j--;
            while (j > 0)
                if (!Character.isLetterOrDigit(s.charAt(j))) j--;
                else break;
        }
        return true;
    }

    public static boolean isPalindrome2(String s) {
        s = s.trim().toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            // System.out.println(i + " ii   " + j + "  jj  ");
            if (!((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= '0' && s.charAt(i) <= '9'))) {
                System.out.println(i + " ii");
                i++;
                continue;
            }
            if (!((s.charAt(j) >= 'a' && s.charAt(j) <= 'z') || (s.charAt(j) >= '0' && s.charAt(j) <= '9'))) {
                System.out.println(j + " jj");
                j--;
                continue;
            }
            if (s.charAt(j) != s.charAt(i)) {
                System.out.println(s.charAt(i) + "    " + s.charAt(j) + "   " + i + "     " + j);
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome2(s));
    }
}

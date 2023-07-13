package com.leetcode.solution;

/**
 * 思路：use two pointer, i from start j from end, convert char to uppercase & watch cases symbol,
 * 关键词： char的操作(Character.toUpperCase & Character.isLetterOrDigit(c))
 * Time & Space complexity: O(n) time O(1) space
 */

public class _125_Valid_Palindrome {
    public boolean isPalindrome(String s) {
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
}

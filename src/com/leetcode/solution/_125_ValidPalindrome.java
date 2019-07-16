package com.leetcode.solution;

/**
 * 思路：use two pointer, i from start j from end, convert char to uppercase & watch cases symbol,
 * 关键词： char的操作(Character.toUpperCase & Character.isLetterOrDigit(c))
 * Time & Space complexity: O(n) time O(1) space
 */

public class _125_ValidPalindrome {
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                char a = Character.toUpperCase(s.charAt(i));
                char b = Character.toUpperCase(s.charAt(j));

                if (a == b) {//A?a
                    i++;
                    j--;
                } else {
                    if (!Character.isLetterOrDigit(a)) i++;//char is not capital letter or number
                    else if (!Character.isLetterOrDigit(b)) j--;
                    else return false;//letters & number not match
                }
            } else {
                i++;
                j--;
            }
        }
        return true;
    }
}

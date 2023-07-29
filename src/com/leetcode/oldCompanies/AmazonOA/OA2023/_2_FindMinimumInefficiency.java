package com.leetcode.oldCompanies.AmazonOA.OA2023;

public class _2_FindMinimumInefficiency {
    public static int minBadChars(String s) {
        int count = 0;
        char[] chars = s.toCharArray();
        char prev = '2'; // Initialize prev as the first character

        // Iterate through the characters of the string from the second character to the last character
        for (int i = 1; i < chars.length; i++) {
            char current = chars[i]; // Current character

            // If the current character is not a '?' and it is different from the previous character, increment the count
            // If the current character is '?', set it to the same as the previous character, unless the previous character is also '?'
            if (current != prev) {
                if (current == '?') {
                    if (prev != '2') {
                        chars[i] = prev;
                    }
                    continue;
                }
                if (prev != '2' || (current != chars[i - 1] && chars[i - 1] != '?')) {
                    count++;
                }
            }
            // Update the previous character for the next iteration
            prev = chars[i];
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(minBadChars("1001001")); // Output: 4
        System.out.println(minBadChars("??011??0")); // Output: 2
        System.out.println(minBadChars("????")); // Output: 0
        System.out.println(minBadChars("111111")); // Output: 0
        System.out.println(minBadChars("000000")); // Output: 0
        System.out.println(minBadChars("0?0?0?0")); // Output: 0
        System.out.println(minBadChars("?0?0?0?")); // Output: 0
    }
}

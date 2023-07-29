package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.HashSet;

public class _13_SensitiveWords {
    public static int findReviewScore(String s, String[] t) {
        HashSet<String> forbiddenSet = new HashSet<>();
        for (String str : t) {
            forbiddenSet.add(str.toLowerCase());
        }
        s = s.toLowerCase();

        int left = 0;
        int right = 0;
        int max_length = 0;
        while (right < s.length()) {
            // 将右指针向右移动，直到遇到违禁字符串或者到达字符串末尾
            while (right < s.length() && isValidSubstring(s.substring(left, right), forbiddenSet)) {
//                System.out.println("current right " + right + " current left " + left + "    " + s.substring(left, right));
                right++;
            }
            // 计算当前窗口的长度并更新最大长度
            if (isValidSubstring(s.substring(left, right), forbiddenSet)) {
                max_length = Math.max(max_length, right - left);
            } else {
                max_length = Math.max(max_length, right - left - 1);
            }
//            System.out.println("here right " + right + " here left " + left + "    " + s.substring(left, right) + " here " + max_length);

            // 移动左指针，直到窗口中不再包含违禁字符串
            while (left < right && isValidSubstring(s.substring(left, right), forbiddenSet)) {
//                System.out.println("this right " + right + " this left " + left + "    " + s.substring(left, right));
                left++;
            }
            // 为了保证下一次计算子串时不包含违禁字符串，左指针再向右移动一位
            if (left < right)
                left++;
//            System.out.println("last right1 " + right + " last left1 " + left);
        }

        return max_length;
    }

    // 判断一个字符串是否是违禁字符串的子串
    private static boolean isValidSubstring(String s, HashSet<String> forbiddenSet) {
        for (String forbiddenStr : forbiddenSet) {
            if (s.contains(forbiddenStr)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String review = "GoodProductButScrapAfterWash";
        String[] prohibitedWords = {"crap", "odpro"};
        System.out.println(findReviewScore(review, prohibitedWords)); // 输出: 15
        String review2 = "FastDeliveryOkayProduct";
        String[] prohibitedWords2 = {"eryoka", "yo", "eli"};
        System.out.println(findReviewScore(review2, prohibitedWords2)); // 输出: 11
        String review3 = "AAAAA";
        String[] prohibitedWords3 = {"a"};
        System.out.println(findReviewScore(review3, prohibitedWords3)); // 输出: 11
        String review4 = "BBBBB";
        String[] prohibitedWords4 = {"a"};
        System.out.println(findReviewScore(review4, prohibitedWords4)); // 输出: 11
    }
}

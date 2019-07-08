package com.leetcode.solution;

import java.util.*;

/**
 * 最短子串有target串
 * <p>
 * Time Complexity: O(∣S∣+∣T∣) where |S| and |T| represent the lengths of strings S and T.
 * In the worst case we might end up visiting every element of string S twice, once by left pointer and once by right pointer.
 * ∣T∣ represents the length of string T.
 * <p>
 * Space Complexity: O(∣S∣+∣T∣). ∣S∣ when the window size is equal to the entire string S. ∣T∣ when T has all unique characters.
 */

public class _076_MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) return "";
        int count = 0;
        String result = "";
        int[] tArr = new int[256]; //target的分布
        for (char c : t.toCharArray()) tArr[c]++; //初始化后不会更新
        int[] sArr = new int[256]; //source的分布
        int left = findNextStrIdx(0, s, tArr); //初始化从0开始
        if (left == s.length()) return ""; //找不到任何match返回空值
        int right = left;// right从left开始
        while (right < s.length()) {
            int rightChar = s.charAt(right);
            if (sArr[rightChar] < tArr[rightChar]) count++; //因为可能s里有t的重复字符，所以必须s里的统计比t小才有效
            sArr[rightChar]++; //即使count不加，sArr也会加
            while (left < s.length() && count == t.length()) { //当count已经集齐，找到有效值
                if (result.isEmpty() || result.length() > right - left + 1) //如果比之前找到的短
                    result = s.substring(left, right + 1); //更新结果
                int leftChar = s.charAt(left);
                if (sArr[leftChar] <= tArr[leftChar]) count--; // 如果此时sArr中的字符小于tArr字符（下一个循环即将不满足t的条件）count变小
                sArr[leftChar]--; //移除最左侧符合的字符
                left = findNextStrIdx(left + 1, s, tArr); //找下一个t中存在的字符
            }
            right = findNextStrIdx(right + 1, s, tArr); //右侧移动寻找
        }
        return result;
    }

    private int findNextStrIdx(int start, String s, int[] tArr) { //找到s下一个t中存在的字符
        while (start < s.length()) { //直到找到s末尾
            char c = s.charAt(start);
            if (tArr[c] != 0) return start;
            start++;
        }
        return start;
    }

    //Wepay例子

    public String minWindow2(String s, Set<Character> t) {
        // check if string's length is less than pattern's
        // length. If yes then no such window can exist
        if (s.length() < t.size()) return "";

        int count = 0;
        String result = "";
        int[] tArr = new int[256]; //target的分布
        for (char c : t) tArr[c]++; //初始化后不会更新
        int[] sArr = new int[256]; //source的分布
        int left = findNextStrIdx(0, s, tArr); //初始化从0开始
        if (left == s.length()) return ""; //找不到任何match返回空值
        int right = left;// right从left开始
        while (right < s.length()) {
            int rightChar = s.charAt(right);
            if (sArr[rightChar] < tArr[rightChar]) count++; //因为可能s里有t的重复字符，所以必须s里的统计比t小才有效
//            if (t.contains(s.charAt(right)) && sArr[rightChar] ==0) count++; //因为可能s里有t的重复字符，所以必须s里的统计比t小才有效
            sArr[rightChar]++; //即使count不加，sArr也会加
            while (left < s.length() && count == t.size()) { //当count已经集齐，找到有效值
                if (result.isEmpty() || result.length() > right - left + 1) //如果比之前找到的短
                    result = s.substring(left, right + 1); //更新结果
                int leftChar = s.charAt(left);
                if (sArr[leftChar] <= tArr[leftChar]) count--; // 如果此时sArr中的字符小于tArr字符（下一个循环即将不满足t的条件）count变小
                sArr[leftChar]--; //移除最左侧符合的字符
                left = findNextStrIdx(left + 1, s, tArr); //找下一个t中存在的字符
            }
            right = findNextStrIdx(right + 1, s, tArr); //右侧移动寻找
        }
        return result;
    }
}

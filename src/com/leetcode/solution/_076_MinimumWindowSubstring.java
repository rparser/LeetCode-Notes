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
 * <p>
 * 1. Use two pointers: start and end to represent a window.
 * 2. Move end to find a valid window.
 * 3. When a valid window is found, move start to find a smaller window.
 * *define counter  =  t.length() *when map.get(c) > 0, counter--, and we remove c's count in map
 * *watch valid case: counter == 0 *return: if len not updated, we not find window
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

    public String minWindowtest(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>(); //<char, count of char in t>
        int start = 0, end = 0; //two pointers, one point to tail and one head
        int minStart = 0; //track the start pos of min string
        int minLen = Integer.MAX_VALUE; //the length of min string
        int counter = t.length(); // counter represents the number of chars of t to be found in s.

        for (char c : s.toCharArray()) map.put(c, 0);  /* initialize the hash map here */
        for (char c : t.toCharArray()) {
            if (map.containsKey(c)) map.put(c, map.get(c) + 1); //必须有此字母，找到则+1
            else return ""; //如果没有此字母则返回空
        }
        while (end < s.length()) {
            char cur = s.charAt(end);
            if (map.get(cur) > 0) counter--; // modify counter here： if cur is in t
            map.put(cur, map.get(cur) - 1); //找到一个就-1，所以无关字母会是负值（初始值为0）
            while (counter == 0) { /* counter condition --find a valid window */
                /* update minLen here if finding minimum*/
                if (minLen > end - start + 1) {
                    minStart = start;
                    minLen = end - start + 1;
                }
                //set back map and counter & start前移直到不合法
                char c2 = s.charAt(start);
                map.put(c2, map.get(c2) + 1); //退一个则一个+1
                if (map.get(c2) > 0) counter++;//出现>0的值，则意味着去掉了重要字母
                start++;
            }
            end++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
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

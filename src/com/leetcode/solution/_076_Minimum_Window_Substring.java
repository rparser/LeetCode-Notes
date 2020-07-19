package com.leetcode.solution;

import java.util.*;

/**
 * 最短子串有target串
 * Time Complexity: O(∣S∣+∣T∣) where |S| and |T| represent the lengths of strings S and T.
 * In the worst case we might end up visiting every element of string S twice, once by left pointer and once by right pointer.
 * ∣T∣ represents the length of string T.
 * Space Complexity: O(∣S∣+∣T∣). ∣S∣ when the window size is equal to the entire string S. ∣T∣ when T has all unique characters.
 * <p>
 * 1. Use two pointers: start and end to represent a window.
 * 2. Move end to find a valid window.
 * 3. When a valid window is found, move start to find a smaller window.
 * *define counter  =  t.length() *when map.get(c) > 0, counter--, and we remove c's count in map
 * *watch valid case: counter == 0 *return: if len not updated, we not find window
 */

public class _076_Minimum_Window_Substring {
    public static String minWindowMap(String str, String pattern) {
        int left = 0, matched = 0, minLength = Integer.MAX_VALUE, resSubStringStart = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char chr : pattern.toCharArray())
            map.put(chr, map.getOrDefault(chr, 0) + 1);

        // try to extend the range [windowStart, windowEnd]
        for (int right = 0; right < str.length(); right++) {
            char rightChar = str.charAt(right);
            if (map.containsKey(rightChar)) {
                map.put(rightChar, map.get(rightChar) - 1);
                if (map.get(rightChar) >= 0) // 小于0意味着需要的字母但是多余了
                    matched++;
            }
            // 如果字母够了，立刻左移left
            // shrink the window if we can, finish as soon as we remove a matched character
            while (matched == pattern.length()) {
                //找到更短的则记录 resSubStringStart
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    resSubStringStart = left;
                }

                char leftChar = str.charAt(left);
                if (map.containsKey(leftChar)) {
                    // note that we could have redundant matching characters, therefore we'll decrement the
                    // matched count only when a useful occurrence of a matched character is going out of the window
                    if (map.get(leftChar) == 0)
                        matched--;

                    map.put(leftChar, map.get(leftChar) + 1);
                }
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : str.substring(resSubStringStart, resSubStringStart + minLength);
    }

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || t.length() > s.length()) return "";
        // 定义一个数字，用来记录字符串 t 中出现字符的频率，也就是窗口内需要匹配的字符和相应的频率
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int left = 0, right = 0;
        int match = 0;  // 匹配字符的个数
        int minLen = s.length() + 1;   // 最大的子串的长度
        // 子串的起始位置 子串结束的位置(如果不存在这样的子串的话，start，end 都是 0，s.substring 截取就是 “”
        int start = 0, end = 0;
        while (right < s.length()) {
            char charRight = s.charAt(right); // 右边界的那个字符
            map[charRight]--;   // 可以理解为需要匹配的字符 charRight 减少了一个
            // 如果字符 charRight 在 t 中存在，那么经过这一次操作，只要个数大于等于 0，说明匹配了一个
            // 若字符 charRight 不在 t 中，那么 map[charRight] < 0, 不进行任何操作
            if (map[charRight] >= 0) match++;
            right++;  // 右边界右移，这样下面就变成了 [)，方便计算窗口大小

            // 只要窗口内匹配的字符达到了要求，右边界固定，左边界收缩
            while (match == t.length()) {
                int size = right - left;
                if (size < minLen) {
                    minLen = size;
                    start = left;
                    end = right;
                }
                char charLeft = s.charAt(left);  // 左边的那个字符
                map[charLeft]++;  // 左边的字符要移出窗口
                // 不在 t 中出现的字符，移出窗口，最终能够达到的最大值 map[charLeft] = 0
                // 如果恰好移出了需要匹配的一个字符，那么这里 map[charLeft] > 0, 也就是还要匹配字符 charLeft，此时 match--
                if (map[charLeft] > 0) match--;
                left++;  // 左边界收缩
            }
        }
        return s.substring(start, end);
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

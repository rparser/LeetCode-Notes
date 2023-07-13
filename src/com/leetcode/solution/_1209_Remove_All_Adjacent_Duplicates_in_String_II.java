package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

//给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，并删除它们，使被删去的字符串的左侧和右侧连在一起。
public class _1209_Remove_All_Adjacent_Duplicates_in_String_II {
    //当前字符与前一个不同时，往栈中压入 1。否则栈顶元素加 1。
//    迭代字符串：
//
//    如果当前字符与前一个相同，栈顶元素加 1。
//
//    否则，往栈中压入 1。
//    如果栈顶元素等于 k，则从字符串中删除这 k 个字符，并将 k 从栈顶移除。
//    注意：因为在 Java 中 Integer 是不可变的，需要先弹出栈顶元素，然后加 1，再压入栈顶。
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Deque<Integer> counts = new LinkedList<>();
        for (int i = 0; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                counts.push(1);
            } else {
                int incremented = counts.pop() + 1;
                if (incremented == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                } else {
                    counts.push(incremented);
                }
            }
        }
        return sb.toString();
    }
}

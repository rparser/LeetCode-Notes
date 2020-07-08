package com.leetcode.solution;

/**
 * 我们首先将原字符串调用trim()来去除冗余空格，然后调用split()来分隔，分隔符设为"\\s+"，
 * 这其实是一个正则表达式，\\s表示空格字符，+表示可以有一个或多个空格字符，那么我们就可以把单词分隔开装入一个字符串数组中，
 * 然后我们从末尾开始，一个个把单词取出来加入结果res中，并且单词之间加上空格字符，注意我们把第一个单词留着不取，
 * 然后返回的时候再加上即可
 */

public class _151_Reverse_Words_in_a_String {
    // 双指针O(N), O(N)
    public String reverseWordsTwoPointers(String s) {
        s = s.trim(); // 删除首尾空格
        int i = s.length() - 1, j = i;
        StringBuilder res = new StringBuilder();
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) != ' ')
                i--; // 搜索首个空格

            res.append(s, i + 1, j + 1).append(" "); // 添加单词, i在前j在后

            while (i >= 0 && s.charAt(i) == ' ')
                i--; // 跳过单词间空格

            j = i; // j 指向下个单词的尾字符
        }
        return res.toString().trim(); // 转化为字符串并返回
    }

    // O(N), O(N)
    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        String[] words = s.trim().split("\\s+");
        for (int i = words.length - 1; i > 0; --i)
            res.append(words[i]).append(" ");
        return res + words[0];
    }
}

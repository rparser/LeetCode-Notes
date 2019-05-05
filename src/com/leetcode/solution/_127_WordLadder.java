package com.leetcode.solution;

import java.util.*;

/**
 * 单词梯子，参考126梯子II
 * BFS做法，需要Queue
 *
 * Time Complexity: O(n*26^l) -> O(n*26^l/2), l = len(word), n=|wordList|
 * Space Complexity: O(n)
 */

public class _127_WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) return 0;

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);

        int length = beginWord.length();
        int steps = 0;

        while (!queue.isEmpty()) {
            ++steps; //加入步数
            for (int s = queue.size(); s > 0; --s) { //处理本层节点
                String currWord = queue.poll();
                char[] currWordChars = currWord.toCharArray();
                for (int i = 0; i < length; i++) {
                    char currChar = currWordChars[i]; //保存这个字母
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (c == currChar) continue; //如果是原值则跳出
                        currWordChars[i] = c; //否则设置为新单词
                        String t = new String(currWordChars);
                        if (t.equals(endWord)) return steps + 1; //如果找到则返回
                        if (!dict.contains(t)) continue; //如果字典里找不到则跳出
                        dict.remove(t); //如果词典里找到，移除这个单词
                        queue.offer(t); //把这个单词加入queue
                    }
                    currWordChars[i] = currChar; //重新放回
                }
            }
        }
        return 0;
    }

    public int ladderLengthBidirectionalBFS(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) return 0;

        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        q1.add(beginWord);
        q2.add(endWord);

        int l = beginWord.length();
        int steps = 0;

        while (!q1.isEmpty() && !q2.isEmpty()) {
            ++steps;

            if (q1.size() > q2.size()) {
                Set<String> tmp = q1;
                q1 = q2;
                q2 = tmp;
            }

            Set<String> q = new HashSet<>();

            for (String w : q1) {
                char[] chs = w.toCharArray();
                for (int i = 0; i < l; ++i) {
                    char ch = chs[i];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        chs[i] = c;
                        String t = new String(chs);
                        if (q2.contains(t)) return steps + 1;
                        if (!dict.contains(t)) continue;
                        dict.remove(t);
                        q.add(t);
                    }
                    chs[i] = ch;
                }
            }

            q1 = q;
        }
        return 0;
    }
}

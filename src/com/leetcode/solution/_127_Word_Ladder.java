package com.leetcode.solution;

import java.util.*;

/**
 * 单词梯子，参考126梯子II
 * BFS做法，需要Queue
 * <p>
 * Time Complexity: O(n*26^l) -> O(n*26^l/2), l = len(word), n=|wordList|
 * Space Complexity: O(n)
 * 模板while,for(每层：步数)，当前单词变字符数组，每个字母可以变任意字母2loop,原词则cont，新单词词典找到则返回，找不到则cont，找到但不是从词典删除加入队列,还原单词
 */

public class _127_Word_Ladder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int length = beginWord.length();
        int steps = 0;

        while (!queue.isEmpty()) {
            steps++; //加入步数
            for (int s = queue.size(); s > 0; s--) { //处理本层节点 - 用size从size到0可以不用再int size
                String curWord = queue.poll();
                for (int i = 0; i < length; i++) { //每个字母都可以变
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == curWord.charAt(i)) {
                            continue;
                        } //如果是原值则跳出
                        String newWord = curWord.substring(0, i) + c + curWord.substring(i + 1); // 变为新单词
                        if (newWord.equals(endWord)) {
                            return steps + 1; // +1是因为多换了一步
                        } //如果找到则返回
                        if (wordSet.contains(newWord)) {//如果词典找到说明还没用过
                            wordSet.remove(newWord);//移除这个单词
                            queue.add(newWord);//把这个单词加入queue
                        }
                    }
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

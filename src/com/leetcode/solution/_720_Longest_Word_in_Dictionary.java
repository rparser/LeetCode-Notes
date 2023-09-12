package com.leetcode.solution;

/**
 * 约等于208 Trie
 */
public class _720_Longest_Word_in_Dictionary {
    static class TrieNode {
        boolean word;
        TrieNode[] children = new TrieNode[26];
    }

    TrieNode root = new TrieNode();

    public void add(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (p.children[u] == null) {
                p.children[u] = new TrieNode();
            }
            p = p.children[u];
        }
        p.word = true;
    }

    public boolean search(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            p = p.children[u];
            if (!p.word) { // 不是单词则返回false（中断了）
                return false;
            }
        }
        return true;
    }

    public String longestWord(String[] words) {
        String result = "";
        // 添加每个单词进列表
        for (String s : words) {
            add(s);
        }
        // 和result比较是否更新result
        for (String s : words) {
            int n = s.length(), m = result.length();
            if (n < m) {
                continue;
            }
            if (n == m && s.compareTo(result) > 0) {
                continue;
            }
            if (search(s)) {
                result = s;
            }
        }
        return result;
    }
}

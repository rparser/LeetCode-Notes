package com.leetcode.solution;

import java.util.*;

/**
 * 查找单词2,参考079图中找单词
 * 模板dfs,主函数遍历，首字母和ij等则&&dfs,boolean(r,c,index)函数，index到长度返T,点不同返F，取点修改为*记录已访问，四方向递归or(相等一个就行)，还原回*字母
 */

public class _212_Word_Search_II {
    private void dfs(char[][] board, int r, int c, TrieNode node, List<String> list) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) return;

        char tmp = board[r][c];
        int n = tmp - 'a';
        if (tmp == '*' || node.next[n] == null) return; //已访问或next空则返回

        node = node.next[n];
        board[r][c] = '*'; //设为已访问
        if (node.word != null) {
            list.add(node.word);
            node.word = null; // One time search.
        }

        dfs(board, r + 1, c, node, list);
        dfs(board, r - 1, c, node, list);
        dfs(board, r, c + 1, node, list);
        dfs(board, r, c - 1, node, list);

        board[r][c] = tmp; //还原临时存储
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    private TrieNode buildTrie(String[] words) { //构造Trie
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        return result;
    }
}

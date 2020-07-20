package com.leetcode.solution;

class _211_Add_and_Search_Word_Data_structure_design {
    public class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public boolean isWord;
    }

    private TrieNode root = new TrieNode();

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null)
                node.children[c - 'a'] = new TrieNode();

            node = node.children[c - 'a'];
        }
        //当前isWord变为True
        node.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    //
    private boolean match(char[] chs, int cur, TrieNode node) {
        if (cur == chs.length)
            return node.isWord;
        // .dot点代表任意字母
        if (chs[cur] == '.') {
            for (int i = 0; i < node.children.length; i++)
                if (node.children[i] != null && match(chs, cur + 1, node.children[i]))
                    return true;
        //如果没有.直接进这里
        } else
            return node.children[chs[cur] - 'a'] != null && match(chs, cur + 1, node.children[chs[cur] - 'a']);

        return false;
    }
}
package com.leetcode.solution;

/**
 * 分析： Trie is a tree data structure, which is used for retrieval of a key in a dataset of strings.应用在 Autocomplete，spell-check等
 * Efficient in: * Finding all keys with a common prefix. *lexicographical order(字典顺序 aab < ab < b.)
 * 思路: Each Tire node can have maximum R(26 for alphabet) links.
 * -- Insert string by char into tree, if null, create new TrieNode .Once reach end char mark the isEnd true
 * -- Search the last char must have the isEnd for true
 * Complexity: O(M=key_length) for insertion & search, M is the length of key
 * Space: O(alphabet(26)*M*N) N is number of keys in trie
 */

public class _208_Implement_Trie_Prefix_Tree {
    // O(M=key_length), O(alphabet(26)*M*N)
    static class TrieNode {
        public boolean is_word;
        public TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[26];
            is_word = false;
        }
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public _208_Implement_Trie_Prefix_Tree() {
        root = new TrieNode();
    }

    /**
     * Returns if the word is in the trie.
     */
    // Search和startsWith都call单独的find method
    public boolean search(String word) {
        TrieNode node = find(word);
        // 如果is_word证明是单词
        return node != null && node.is_word;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = find(prefix);
        return node != null;
    }

    private TrieNode find(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (p.children[index] == null) {
                return null; //找不到就返回
            }
            // 找到符合这个prefix的TrieNode
            p = p.children[index];
        }
        return p;
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (p.children[index] == null) {
                // 新建一个TrieNode然后重新指向这个TrieNode
                p.children[index] = new TrieNode();
            }
            p = p.children[index];
        }
        // 在最后一步跳出for loop后添加is_word =true;
        p.is_word = true;
    }
}

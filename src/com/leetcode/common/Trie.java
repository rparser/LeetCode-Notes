package com.leetcode.common;

// Java program to print all words in the CamelCase
// dictionary that matches with a given pattern

import java.util.*;

public class Trie {

    // Alphabet size (# of upper-Case characters)
    private static final int ALPHABET_SIZE = 26;


    // A Trie node
    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        // isLeaf is true if the node represents
        // end of a word
        boolean isLeaf;

        // vector to store list of complete words
        // in leaf node
        List<String> word;

        private TrieNode() {
            isLeaf = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;

            word = new ArrayList<>();
        }
    }

    private static TrieNode root;

    // Function to insert word into the Trie
    private static void insert(String word) {
        int index;
        TrieNode pCrawl = root;

        for (int level = 0; level < word.length(); level++) {

            // consider only uppercase characters
            if (Character.isLowerCase(word.charAt(level)))
                continue;

            // get current character position
            index = word.charAt(level) - 'A';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }

        // mark last node as leaf
        pCrawl.isLeaf = true;

        // push word into vector associated with leaf node
        (pCrawl.word).add(word);
    }

    // Function to print all children of Trie node root
    private static void printAllWords(TrieNode root) {

        // if current node is leaf
        if (root.isLeaf) {
            for (String str : root.word)
                System.out.println(str);
        }

        // recurse for all children of root node
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            TrieNode child = root.children[i];
            if (child != null)
                printAllWords(child);
        }
    }

    // search for pattern in Trie and print all words
    // matching that pattern
    private static boolean search(String pattern) {
        int index;
        TrieNode pCrawl = root;

        for (int level = 0; level < pattern.length(); level++) {
            index = pattern.charAt(level) - 'A';

            // Invalid pattern
            if (pCrawl.children[index] == null)
                return false;

            pCrawl = pCrawl.children[index];
        }

        // print all words matching that pattern
        printAllWords(pCrawl);

        return true;
    }

    // Main function to print all words in the CamelCase
    // dictionary that matches with a given pattern
    private static void findAllWords(List<String> dict, String pattern) {

        // construct Trie root node
        root = new TrieNode();

        // Construct Trie from given dict
        for (String word : dict)
            insert(word);

        // search for pattern in Trie
        if (!search(pattern))
            System.out.println("No match found");
    }

    // Driver function
    public static void main(String[] args) {

        // dictionary of words where each word follows
        // CamelCase notation
        List<String> dict = Arrays.asList("Hi", "Hello",
                "HelloWorld", "HiTech", "HiGeek",
                "HiTechWorld", "HiTechCity",
                "HiTechLab");

        // pattern consisting of uppercase characters only
        String pattern = "HT";

        findAllWords(dict, pattern);
    }
}

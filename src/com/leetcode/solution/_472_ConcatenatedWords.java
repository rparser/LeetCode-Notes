package com.leetcode.solution;

import java.util.*;

/**
 * 把所有的单词放入HashSet中，然后每个单词去判断。判断的时候使用递归即可。
 * 算法复杂度为O(nm)。n为数组长度,m为数组中字符串平均长度。
 */

public class _472_ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> allWords = new HashSet<>(Arrays.asList(words));

        for (String word : words)
            if (checkConcatenated(word, 0, allWords, 0))
                result.add(word);
        return result;
    }

    private boolean checkConcatenated(String word, int index, Set<String> words, int currentWordNum) {
        if (index >= word.length() && currentWordNum >= 2) return true;

        for (int i = index; i < word.length(); i++) //遍历这个单词
            // 如果
            if (words.contains(word.substring(index, i + 1)) && checkConcatenated(word, i + 1, words, currentWordNum + 1))
                return true;
        return false;
    }
}

package com.leetcode.solution;

import java.util.*;

public class _139_WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        return false;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> allWords = new HashSet<>();
        List<String> result = new ArrayList<>();
        for(String word : words){
            allWords.add(word);
        }

        for(String word : words){
            if(checkConcatenated(word, 0, allWords, 0)){
                result.add(word);
            }
        }
        return result;
    }

    private boolean checkConcatenated(String word, int index, Set<String> words, int currentWordNum){
        if(index >= word.length() && currentWordNum >= 2){
            return true;
        }
        for(int i = index; i < word.length(); i++){
            if(words.contains(word.substring(index, i + 1))){
                if(checkConcatenated(word, i + 1, words, currentWordNum + 1)){
                    return true;
                }
            }
        }
        return false;
    }
}

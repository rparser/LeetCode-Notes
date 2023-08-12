package com.leetcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * O(n * m) + O(n * num) runtime, O(n) space
 * where n is the length of the string
 * m is the length of the longest string in the dictionary
 * num is the number of solutions
 */

public class _140_Word_Break_II {
    //O(n * m) + O(n * num), O(n)
    private final HashMap<String, List<String>> map = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (map.containsKey(s)) {
            return map.get(s); //找到结果
        }
        if (wordDict.contains(s)) {
            result.add(s); //把wordDict先加入map
        }

        //遍历每个字母
        for (int i = 1; i < s.length(); i++) {
            //判断从i到结尾的单词有没有
            // "catsanddog" 找到的顺序 dog -> sand-> and
            String subWord = s.substring(i);
            //如果有
            if (wordDict.contains(subWord)) {
                //看能不能找到前面的单词
                List<String> subWordBreak = wordBreak(s.substring(0, i), wordDict);
                //如果前面也有，则分别加入结果
                if (!subWordBreak.isEmpty())
                    for (String subSubWord : subWordBreak)
                        result.add(subSubWord + " " + subWord);
            }
        }
        // map最后保存各个片段，最后一个弹出的就是result
        map.put(s, result);
        return result;
    }
}

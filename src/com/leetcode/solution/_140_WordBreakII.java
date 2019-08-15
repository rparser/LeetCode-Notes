package com.leetcode.solution;

import java.util.*;

public class _140_WordBreakII {
    private HashMap<String, List<String>> map = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        if (map.containsKey(s)) return map.get(s); //找到结果
        if (wordDict.contains(s)) res.add(s); //把wordDict先加入map

        for (int i = 1; i < s.length(); i++) { //遍历每个字母
            String subWord = s.substring(i); //从1到结尾开始的单词
            if (wordDict.contains(subWord)) { //如果找到了新单词
                List<String> subWordBreak = wordBreak(s.substring(0, i), wordDict); //看能不能找到前面
                if (subWordBreak.size() != 0) //如果找到前面
                    for (String subSubWord : subWordBreak) res.add(subSubWord + " " + subWord); //加入结果
            }
        }
        map.put(s, res);
        return res;
    }
}

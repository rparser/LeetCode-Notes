package com.leetcode.solution;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class _824_goat_latin {
    // O(N), O(1)
    public String toGoatLatin(String S) {
        Set<Character> vowel = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        //单词个数
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (String word : S.split(" ")) {
            //如果是原因开头直接加入
            if (vowel.contains(word.charAt(0)))
                sb.append(word);
                //否则辅音放到最后再加入第一个字母
            else {
                sb.append(word.substring(1));
                sb.append(word, 0, 1);
            }
            //加ma
            sb.append("ma");
            // 最后添加a
            for (int i = 0; i < count; i++)
                sb.append("a");
            //下个单词
            count++;
            sb.append(" ");
        }
        //删除最后的空格
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}

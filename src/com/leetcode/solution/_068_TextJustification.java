package com.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、算出当前行能放的单词数和所用长度；2、如果当前行将包括最后一个单词或者只包含一个单词，则右边不全空格；
 * 3、否则，算出单词间平均补全的空格数，以及左侧需要额外加1的空格位置。
 * 比较麻烦的字符串细节实现题。需要解决以下几个问题：
 * <p>
 * 1. 首先要能判断多少个word组成一行：
 * 这里统计读入的所有words的总长curLen，并需要计算空格的长度。假如已经读入words[0:i]。当curLen + i <=L 且加curLen + 1 + word[i+1].size() > L时，一行结束。
 * <p>
 * 2. 知道一行的所有n个words，以及总长curLen之后要决定空格分配：
 * 平均空格数：k = (L - curLen) / (n-1)
 * 前m组每组有空格数k+1：m = (L - curLen) % (n-1)
 * <p>
 * 例子：L = 21，curLen = 14，n = 4
 * k = (21 - 14) / (4-1) = 2
 * m = (21 - 14) % (4-1)  = 1
 * A---B--C--D
 * <p>
 * 3. 特殊情况：
 * (a) 最后一行：当读入到第i = words.size()-1 个word时为最后一行。该行k = 1，m = 0
 * (b) 一行只有一个word：此时n-1 = 0，计算(L - curLen)/(n-1)会出错。该行k = L-curLen, m = 0
 * (c) 当word[i].size() == L时。
 */

public class _068_TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) return res;

        for (int index = 0; index < words.length; ) {
            StringBuilder buff = new StringBuilder();
            int count = words[index].length(); // 本行单词长度
            int last = index + 1;
            while (last < words.length && count + 1 + words[last].length() <= maxWidth) { // 1为空格
                count += 1 + words[last].length(); //加到最后一个单词，或长度超出
                last++;
            }

            int diff = last - index - 1;
            if (last == words.length || diff == 0) { //如果没有排满，空格放到最后
                for (int i = index; i < last; i++) buff.append(words[i] + ' '); //如果是最后一行，加到最后一个单词
                buff.deleteCharAt(buff.length() - 1); //最后一个单词后会多一个空格，要去掉空格
                for (int i = buff.length() + 1; i <= maxWidth; i++) buff.append(' '); //再补齐空格
            } else {
                int spaces = (maxWidth - count) / diff; //各自分配几个空格
                int left = (maxWidth - count) % diff; //余下几个不可分配的空格
                for (int i = index; i < last; i++) {
                    buff.append(words[i]); //添加单词
                    if (i < last - 1) { //
                        for (int j = 0; j <= spaces + (i - index < left ? 1 : 0); j++) { //看分配几个空格（是不是前left个）
                            buff.append(' ');
                        }
                    }
                }
            }
            res.add(buff.toString());
            index = last; // 开始下一行
        }
        return res;
    }
}

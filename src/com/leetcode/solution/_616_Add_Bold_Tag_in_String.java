package com.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

public class _616_Add_Bold_Tag_in_String {
    // O(S * N * K) K为单词长度, O(N)
    public String addBoldTag(String str, String[] dict) {
        if (dict.length == 0)
            return str;

        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < str.length(); i++)
            for (String word : dict)
                //遍历每个word，如果某个i startsWith这个word则加入结果
                if (str.startsWith(word, i))
                    // 左闭右闭，右侧也是包含的
                    intervals.add(new int[]{i, i + word.length() - 1});

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int index = 0;
        while (i < intervals.size()) {
            int left = intervals.get(i)[0];
            int right = intervals.get(i)[1];
            //(str,start,end)先把前半段加入,left不包括因为右开
            sb.append(str, index, left);
            //合并区间,当前右大于最近的左，说明有overlap，需要merge
            // -1是因为,因为上面是左闭右闭，所以比如[4,7]下个是[8,10]也需要merge
            while (i < intervals.size() && right >= intervals.get(i)[0] - 1) {
                right = Math.max(right, intervals.get(i)[1]);
                i++;
            }
            // 加上区间内的值
            sb.append("<b>").append(str, left, right + 1).append("</b>");
            index = right + 1;
        }

        if (index <= str.length() - 1)
            sb.append(str.substring(index));

        return sb.toString();
    }


    public boolean match(String S, int i, int j, String T) {
        for (int k = i; k < j; ++k)
            if (k >= S.length() || S.charAt(k) != T.charAt(k - i))
                return false;
        return true;
    }

    public String addBoldTag2(String S, String[] words) {
        int N = S.length();
        boolean[] flag = new boolean[N];
        for (int i = 0; i < N; ++i) {
            // S的起始的字符，每个都要遍历到
            for (String word : words) {
                // S以i起始的字符长度为word.length(),是否和word匹配
                if (!match(S, i, i + word.length(), word)) {
                    // 不匹配则看下一个word
                    continue;
                }
                // 匹配则置标记位
                for (int j = i; j < i + word.length(); ++j) {
                    flag[j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < flag.length; i++) {
            if ((i == 0 && flag[i]) || (flag[i] && !flag[i - 1])) {
                sb.append("<b>");
            }
            sb.append(S.charAt(i));
            if ((i == flag.length - 1 && flag[i]) || (flag[i] && !flag[i + 1])) {
                sb.append("</b>");
            }
        }
        return sb.toString();
    }
}
